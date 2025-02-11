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

@UtilityClass
public class ObjectMapperUtil {

    public List<PlanFeatureResponse> mapToPlanFeatureResponse(List<PlanFeature> planFeatures) {
        if (CollectionUtils.isEmpty(planFeatures)) {
            return new ArrayList<>();
        }
        return planFeatures.stream()
                .map(ObjectMapperUtil::mapToPlanFeatureResponse)
                .toList();
    }

    private PlanFeatureResponse mapToPlanFeatureResponse(PlanFeature planFeature) {
        return PlanFeatureResponse.builder()
                .id(planFeature.getId())
                .featureType(planFeature.getFeatureType())
                .featureValue(planFeature.getFeatureValue())
                .featureDescription(planFeature.getFeatureDescription())
                .build();
    }
}
