package com.abha.aums.subscription.services.impl;

import static com.abha.sharedlibrary.shared.common.ExceptionUtil.buildException;

import com.abha.aums.exceptions.AbhaExceptions;
import com.abha.aums.integration.enms.NotificationService;
import com.abha.aums.subscription.daos.AppSubscriberDao;
import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.services.AppSubscriberService;
import com.abha.aums.subscription.services.AppSubscriptionService;
import com.abha.aums.subscription.services.SubscriptionPlanService;
import com.abha.aums.subscription.utils.ObjectMapperUtil;
import com.abha.aums.users.models.User;
import com.abha.aums.users.services.UserService;
import com.abha.aums.utils.CommonUtils;
import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.aums.response.SignupResponse;
import com.abha.sharedlibrary.shared.common.Utils;
import com.abha.sharedlibrary.shared.enums.PlanType;
import com.abha.sharedlibrary.shared.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

@Service
public class AppSubscriberServiceImpl implements AppSubscriberService {

  private final AppSubscriberDao appSubscriberDao;
  private final UserService userService;
  private final NotificationService notificationService;
  private final SubscriptionPlanService subscriptionPlanService;
  private final AppSubscriptionService appSubscriptionService;

  @Autowired
  public AppSubscriberServiceImpl(
      AppSubscriberDao appSubscriberDao, UserService userService, NotificationService notificationService,
      SubscriptionPlanService subscriptionPlanService, AppSubscriptionService appSubscriptionService) {
    this.appSubscriberDao = appSubscriberDao;
    this.userService = userService;
    this.notificationService = notificationService;
    this.subscriptionPlanService = subscriptionPlanService;
    this.appSubscriptionService = appSubscriptionService;
  }

  @Transactional
  @Override
  public SignupResponse registerSubscriber(RequestEntity<SignupRequest> signupRequestRequestEntity) {
    SignupRequest signupRequest = signupRequestRequestEntity.getBody();
    appSubscriberDao.findByEmailAndStatusNot(signupRequest.getEmail(), Status.DELETED)
        .ifPresent(s -> {throw buildException(AbhaExceptions.ACCOUNT_ALREADY_PRESENT);});
    AppSubscriber appSubscriber = ObjectMapperUtil.mapToSaveSubscriber(signupRequestRequestEntity);
    AppSubscriber savedAppSubscriber = appSubscriberDao.saveAppSubscriber(appSubscriber);
    AppSubscriptions appSubscriptions = mapToSaveAppSubscriptions(appSubscriber, signupRequest);
    appSubscriptionService.saveAppSubscription(appSubscriptions);
    User user = ObjectMapperUtil.mapToSaveUser(signupRequestRequestEntity, savedAppSubscriber);
    User savedUser = userService.saveUser(user);
    return null;
  }

  private AppSubscriptions mapToSaveAppSubscriptions(
      AppSubscriber appSubscriber) {
    SubscriptionPlan subscriptionPlan = subscriptionPlanService.fetchPlanByTypeAndStatus(
        PlanType.TRIAL, Status.ACTIVE)
        .orElseThrow(() -> buildException(AbhaExceptions.TRIAL_PLAN_MISSING));
    return AppSubscriptions.builder()
        .appSubscriber(appSubscriber)
        .subscriptionPlan(subscriptionPlan)
        .startDateTime(Utils.getDatePlusDays(0))
        .endDateTime(Utils.getDatePlusDays(CommonUtils.getDaysByCycle(subscriptionPlan.getPlanCycle())))
        .build();
  }
}
