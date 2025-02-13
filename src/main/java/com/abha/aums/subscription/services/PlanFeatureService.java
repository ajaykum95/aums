package com.abha.aums.subscription.services;

import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import java.util.List;

/**
 * Service interface for managing plan features.
 */
public interface PlanFeatureService {
  /**
   * Fetches all features associated with a given subscription plan.
   *
   * @param subscriptionPlan the subscription plan for which features are to be fetched
   * @return a list of plan features associated with the given subscription plan
   */
  List<PlanFeature> fetchAllPlanFeatures(SubscriptionPlan subscriptionPlan);
}
