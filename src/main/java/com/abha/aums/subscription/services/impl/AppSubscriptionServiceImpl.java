package com.abha.aums.subscription.services.impl;

import static com.abha.sharedlibrary.shared.common.ExceptionUtil.buildException;

import com.abha.aums.exceptions.AbhaExceptions;
import com.abha.aums.integration.pams.PaymentService;
import com.abha.aums.subscription.daos.AppSubscriberDao;
import com.abha.aums.subscription.daos.AppSubscriptionDao;
import com.abha.aums.subscription.daos.SubscriptionPlanDao;
import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.subscription.models.PendingSubscriptionPlan;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.services.AppSubscriptionService;
import com.abha.aums.subscription.utils.ObjectMapperUtil;
import com.abha.aums.users.daos.UserDao;
import com.abha.aums.users.models.User;
import com.abha.aums.utils.AppConstant;
import com.abha.aums.utils.CommonUtils;
import com.abha.sharedlibrary.aums.request.SubscriptionUpgradeReq;
import com.abha.sharedlibrary.pams.request.PaymentInfoRequest;
import com.abha.sharedlibrary.pams.response.PaymentInfo;
import com.abha.sharedlibrary.pams.response.PaymentInfoResponse;
import com.abha.sharedlibrary.shared.common.response.CommonResponse;
import com.abha.sharedlibrary.shared.enums.ActivityStatus;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
public class AppSubscriptionServiceImpl implements AppSubscriptionService {

  private final AppSubscriptionDao appSubscriptionDao;
  private final AppSubscriberDao appSubscriberDao;
  private final SubscriptionPlanDao subscriptionPlanDao;
  private final PaymentService paymentService;
  private final UserDao userDao;

  @Autowired
  public AppSubscriptionServiceImpl(
      AppSubscriptionDao appSubscriptionDao, AppSubscriberDao appSubscriberDao,
      SubscriptionPlanDao subscriptionPlanDao, PaymentService paymentService, UserDao userDao) {
    this.appSubscriptionDao = appSubscriptionDao;
    this.appSubscriberDao = appSubscriberDao;
    this.subscriptionPlanDao = subscriptionPlanDao;
    this.paymentService = paymentService;
    this.userDao = userDao;
  }

  @Override
  public AppSubscriptions saveAppSubscription(AppSubscriptions appSubscriptions) {
    return appSubscriptionDao.saveAppSubscription(appSubscriptions);
  }

  @Override
  public CommonResponse upgradeSubscriptionPlan(
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity) {
    SubscriptionUpgradeReq subscriptionUpgradeReq = subscriptionUpgradeReqEntity.getBody();
    AppSubscriber appSubscriber = appSubscriberDao.findByIdAndStatusNot(
        subscriptionUpgradeReq.getSubscriberId(), Status.DELETED);
    SubscriptionPlan subscriptionPlan = subscriptionPlanDao.getPlanByIdAndStatusNot(
        subscriptionUpgradeReq.getSubscriptionPlanId(), Status.DELETED);
    validatePayment(subscriptionUpgradeReqEntity, subscriptionPlan, appSubscriber);
    proceedUpgradeSubscriptionPlan(appSubscriber, subscriptionUpgradeReqEntity, subscriptionPlan);
    return new CommonResponse(AppConstant.PLAN_UPGRADED);
  }

  private void proceedUpgradeSubscriptionPlan(
      AppSubscriber appSubscriber, RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity,
      SubscriptionPlan subscriptionPlan) {
    AppSubscriptions oldAppSubscriptions = appSubscriptionDao.getActiveSubscriptionPlan(appSubscriber);
    List<User> userList = userDao.getAllUserBySubscription(oldAppSubscriptions);
    String userId = CommonUtils.getUserId(subscriptionUpgradeReqEntity);
    if (Objects.nonNull(oldAppSubscriptions)) {
      oldAppSubscriptions.setStatus(Status.INACTIVE);
      oldAppSubscriptions.setUpdatedBy(userId);
    }
    AppSubscriptions newAppSubscription = ObjectMapperUtil.mapToSaveSubscriptionPlan(
        subscriptionPlan, appSubscriber, userId);
    appSubscriptionDao.saveAllSubscription(List.of(oldAppSubscriptions, newAppSubscription));
    List<User> users = userList.stream().peek(user -> {
      user.setAppSubscriptions(newAppSubscription);
      user.setUpdatedBy(userId);
    }).toList();
    userDao.saveAllUsers(users);
  }

  private void validatePayment(
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity,
      SubscriptionPlan subscriptionPlan, AppSubscriber appSubscriber) {
    try {
      PaymentInfoResponse paymentInfoResponse = paymentService.fetchPaymentDetails(
          buildPaymentInfoRequest(subscriptionUpgradeReqEntity),
          subscriptionUpgradeReqEntity.getHeaders());
      if (Objects.isNull(paymentInfoResponse)
          || CollectionUtils.isEmpty(paymentInfoResponse.getPaymentInfo())) {
        throw buildException(AbhaExceptions.PAYMENT_INFO_NOT_FOUND);
      }
      PaymentInfo paymentInfo = paymentInfoResponse.getPaymentInfo().get(0);
      if (paymentInfo.getAmount() < subscriptionPlan.getPrice()) {
        handlePaymentFailure(appSubscriber, subscriptionPlan, subscriptionUpgradeReqEntity, ActivityStatus.REJECTED,
            AbhaExceptions.SUBSCRIPTION_PAN_AMOUNT_INVALID);
      }
    } catch (Exception e) {
      log.error("An error occurred while upgrading subscription plan Error: {}",
          ExceptionUtils.getStackTrace(e));
      handlePaymentFailure(appSubscriber, subscriptionPlan, subscriptionUpgradeReqEntity, ActivityStatus.PENDING,
          AbhaExceptions.SOMETHING_WENT_WRONG);
    }
  }

  private void handlePaymentFailure(
      AppSubscriber appSubscriber, SubscriptionPlan subscriptionPlan,
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity, ActivityStatus status,
      AbhaExceptions exception) {
    updatePendingPayment(appSubscriber, subscriptionPlan, status, subscriptionUpgradeReqEntity);
    throw buildException(exception);
  }

  private void updatePendingPayment(
      AppSubscriber appSubscriber, SubscriptionPlan subscriptionPlan, ActivityStatus activityStatus,
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity) {
    PendingSubscriptionPlan pendingSubscriptionPlan = ObjectMapperUtil.mapToSavePendingPayment(
        appSubscriber, subscriptionPlan, subscriptionUpgradeReqEntity, activityStatus);
    subscriptionPlanDao.savePendingSubscriptionPlan(pendingSubscriptionPlan);
  }

  private PaymentInfoRequest buildPaymentInfoRequest(
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity) {
    return PaymentInfoRequest.builder()
        .id(subscriptionUpgradeReqEntity.getBody().getPaymentEntityId())
        .build();
  }
}
