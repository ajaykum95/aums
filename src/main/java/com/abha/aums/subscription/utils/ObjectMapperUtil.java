package com.abha.aums.subscription.utils;

import com.abha.aums.shared.models.CrmPriority;
import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.subscription.models.PendingSubscriptionPlan;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.users.models.User;
import com.abha.aums.utils.CommonUtils;
import com.abha.aums.utils.SecurityUtil;
import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.aums.request.SubscriberDetailsRequest;
import com.abha.sharedlibrary.aums.request.SubscriptionUpgradeReq;
import com.abha.sharedlibrary.aums.response.PlanFeatureResponse;
import com.abha.sharedlibrary.shared.common.Utils;
import com.abha.sharedlibrary.shared.enums.ActivityStatus;
import com.abha.sharedlibrary.shared.enums.Gender;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;
import org.springframework.http.RequestEntity;
import org.springframework.util.CollectionUtils;

/**
 * Utility class for mapping entities to response DTOs.
 */
@UtilityClass
public class ObjectMapperUtil {

  /**
   * Maps a list of PlanFeature entities to a list of PlanFeatureResponse DTOs.
   *
   * @param planFeatures the list of plan features to map
   * @return a list of mapped PlanFeatureResponse objects
   */
  public List<PlanFeatureResponse> mapToPlanFeatureResponse(List<PlanFeature> planFeatures) {
    if (CollectionUtils.isEmpty(planFeatures)) {
      return new ArrayList<>();
    }
    return planFeatures.stream()
        .map(ObjectMapperUtil::mapToPlanFeatureResponse)
        .toList();
  }

  /**
   * Maps a single PlanFeature entity to a PlanFeatureResponse DTO.
   *
   * @param planFeature the plan feature to map
   * @return the mapped PlanFeatureResponse object
   */
  private PlanFeatureResponse mapToPlanFeatureResponse(PlanFeature planFeature) {
    return PlanFeatureResponse.builder()
        .id(planFeature.getId())
        .featureType(planFeature.getFeatureType())
        .featureValue(planFeature.getFeatureValue())
        .featureDescription(planFeature.getFeatureDescription())
        .build();
  }

  public AppSubscriber mapToSaveSubscriber(
      RequestEntity<SignupRequest> signupRequestRequestEntity) {
    SignupRequest signupRequest = signupRequestRequestEntity.getBody();
    return AppSubscriber.builder()
        .firstName(signupRequest.getFirstName())
        .lastName(signupRequest.getLastName())
        .email(signupRequest.getEmail())
        .status(Status.ACTIVE)
        .createdBy("0")
        .build();
  }

  public User mapToSaveUser(RequestEntity<SignupRequest> signupRequestRequestEntity,
                            AppSubscriptions appSubscriptions, Integer pictureDocId) {
    SignupRequest signupRequest = signupRequestRequestEntity.getBody();
    return User.builder()
        .appSubscriptions(appSubscriptions)
        .profilePictureId(pictureDocId)
        .firstName(signupRequest.getFirstName())
        .lastName(signupRequest.getLastName())
        .gender(Gender.NTS)
        .email(signupRequest.getEmail())
        .isEmailVerified(false)
        .isMobileVerified(false)
        .password(SecurityUtil.hashPassword(signupRequest.getPassword()))
        .status(Status.ACTIVE)
        .createdBy("0")
        .token(SecurityUtil.generateSecureToken())
        .build();
  }

  public static String buildEmailVerificationTemplateParams(User savedUser) {
    //TODO
    return "";
  }

  public static void mapToUpdaTeAppSubscriber(
      AppSubscriber appSubscriber, SubscriberDetailsRequest subscriberDetailsRequest,
      Set<CrmPriority> crmPriorities) {
    appSubscriber.setPhone(subscriberDetailsRequest.getPhone());
    appSubscriber.setEntityRef(subscriberDetailsRequest.getEntityReference());
    appSubscriber.setCompanyName(subscriberDetailsRequest.getCompanyName());
    appSubscriber.setWebsite(subscriberDetailsRequest.getWebsite());
    appSubscriber.setCompanySize(subscriberDetailsRequest.getCompanySize());
    appSubscriber.setSalesTeamSize(subscriberDetailsRequest.getSalesTeamSize());
    appSubscriber.setIndustry(subscriberDetailsRequest.getIndustry());
    appSubscriber.setCrmPriorities(crmPriorities);
  }

  public static AppSubscriptions mapToSaveSubscriptionPlan(
      SubscriptionPlan subscriptionPlan, AppSubscriber appSubscriber, String userId) {
    return AppSubscriptions.builder()
        .appSubscriber(appSubscriber)
        .subscriptionPlan(subscriptionPlan)
        .startDateTime(Utils.getDatePlusDays(0))
        .endDateTime(Utils.getDatePlusDays(CommonUtils.getDaysByCycle(subscriptionPlan.getPlanCycle())))
        .status(Status.ACTIVE)
        .createdBy(userId)
        .build();
  }

  public static PendingSubscriptionPlan mapToSavePendingPayment(
      AppSubscriber appSubscriber, SubscriptionPlan subscriptionPlan,
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity, ActivityStatus activityStatus) {
    String userId = CommonUtils.getUserId(subscriptionUpgradeReqEntity);
    return PendingSubscriptionPlan.builder()
        .createdBy(userId)
        .appSubscriber(appSubscriber)
        .subscriptionPlan(subscriptionPlan)
        .paymentEntityId(subscriptionUpgradeReqEntity.getBody().getPaymentEntityId())
        .activityStatus(activityStatus)
        .build();
  }
}
