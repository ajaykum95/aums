package com.abha.aums.subscription.services;

import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import java.util.List;

public interface PlanFeatureService {
    List<PlanFeature> fetchAllPlanFeatures(SubscriptionPlan subscriptionPlan);
}
