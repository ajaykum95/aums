package com.abha.aums.subscription.utils;

import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.aums.response.PlanFeatureResponse;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
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
}
