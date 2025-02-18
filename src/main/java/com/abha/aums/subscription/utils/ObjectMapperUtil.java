package com.abha.aums.subscription.utils;

import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.users.models.User;
import com.abha.aums.utils.CommonUtils;
import com.abha.aums.utils.SecurityUtil;
import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.aums.response.PlanFeatureResponse;
import com.abha.sharedlibrary.shared.enums.Gender;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.ArrayList;
import java.util.List;
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
}
