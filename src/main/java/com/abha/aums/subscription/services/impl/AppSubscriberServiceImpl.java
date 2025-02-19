package com.abha.aums.subscription.services.impl;

import static com.abha.sharedlibrary.shared.common.ExceptionUtil.buildException;

import com.abha.aums.config.EmailConfigMap;
import com.abha.aums.config.EmailTemplateConfig;
import com.abha.aums.config.TemplatesConfigMap;
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
import com.abha.sharedlibrary.enms.enums.Language;
import com.abha.sharedlibrary.enms.enums.NotificationType;
import com.abha.sharedlibrary.enms.request.EmailMetadata;
import com.abha.sharedlibrary.enms.request.SendNotificationRequest;
import com.abha.sharedlibrary.shared.common.Utils;
import com.abha.sharedlibrary.shared.enums.PlanType;
import com.abha.sharedlibrary.shared.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

@Service
public class AppSubscriberServiceImpl implements AppSubscriberService {

  private final AppSubscriberDao appSubscriberDao;
  private final UserService userService;
  private final NotificationService notificationService;
  private final SubscriptionPlanService subscriptionPlanService;
  private final AppSubscriptionService appSubscriptionService;
  @Value("${profile.picture.default.pictureDocId}")
  private Integer pictureDocId;
  private final TemplatesConfigMap templatesConfigMap;
  private final EmailConfigMap emailConfigMap;

  @Autowired
  public AppSubscriberServiceImpl(
      AppSubscriberDao appSubscriberDao, UserService userService, NotificationService notificationService,
      SubscriptionPlanService subscriptionPlanService, AppSubscriptionService appSubscriptionService,
      TemplatesConfigMap templatesConfigMap, EmailConfigMap emailConfigMap) {
    this.appSubscriberDao = appSubscriberDao;
    this.userService = userService;
    this.notificationService = notificationService;
    this.subscriptionPlanService = subscriptionPlanService;
    this.appSubscriptionService = appSubscriptionService;
    this.templatesConfigMap = templatesConfigMap;
    this.emailConfigMap = emailConfigMap;
  }

  @Transactional
  @Override
  public SignupResponse registerSubscriber(RequestEntity<SignupRequest> signupRequestRequestEntity) {
    SignupRequest signupRequest = signupRequestRequestEntity.getBody();
    validateSubscriberAnUser(signupRequest);
    AppSubscriber savedAppSubscriber = appSubscriberDao.saveAppSubscriber(
        ObjectMapperUtil.mapToSaveSubscriber(signupRequestRequestEntity));
    AppSubscriptions appSubscriptions = appSubscriptionService.saveAppSubscription(
        mapToSaveAppSubscriptions(savedAppSubscriber));
    User user = ObjectMapperUtil.mapToSaveUser(signupRequestRequestEntity, appSubscriptions, pictureDocId);
    User savedUser = userService.saveUser(user);
    sendVerifyMailToSubscriber(savedUser);
    return new SignupResponse(savedAppSubscriber.getId());
  }

  private void sendVerifyMailToSubscriber(User savedUser) {
    String params = ObjectMapperUtil.buildEmailVerificationTemplateParams(savedUser);
    EmailTemplateConfig configMapEmails = templatesConfigMap.getEmails();
    SendNotificationRequest sendNotificationRequest = SendNotificationRequest.builder()
        .createdBy(savedUser.getId().toString())
        .customerId(savedUser.getId())
        .notificationType(NotificationType.EMAIL_VERIFICATION)
        .preferredLanguage(Language.EN)
        .emailInfo(EmailMetadata.builder()
            .parameterMap(params)
            .domsTemplateId(configMapEmails.getEmailVerify())
            .sendTo(savedUser.getEmail())
            .sendFrom(emailConfigMap.getPrimary())
            .build())
        .build();
    notificationService.sendEmailVerificationMail(sendNotificationRequest);
  }

  private void validateSubscriberAnUser(SignupRequest signupRequest) {
    appSubscriberDao.findByEmailAndStatusNot(signupRequest.getEmail(), Status.DELETED)
        .ifPresent(s -> {
          throw buildException(AbhaExceptions.ACCOUNT_ALREADY_PRESENT);
        });
    userService.findByEmailAndStatusNot(signupRequest.getEmail(), Status.DELETED)
        .ifPresent(u -> {
          throw buildException(AbhaExceptions.USER_ACCOUNT_ALREADY_PRESENT);
        });
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
        .status(Status.ACTIVE)
        .createdBy("0")
        .build();
  }
}
