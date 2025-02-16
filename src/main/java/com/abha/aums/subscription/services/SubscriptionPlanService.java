package com.abha.aums.subscription.services;

import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import com.abha.sharedlibrary.shared.enums.PlanType;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing subscription plans.
 */
public interface SubscriptionPlanService {
  /**
   * Fetches all active subscription plans.
   *
   * @return a list of active subscription plans
   */
  List<SubscriptionPlanResponse> fetchAllActiveSubscriptionPlans();

  Optional<SubscriptionPlan> fetchPlanByTypeAndStatus(PlanType planType, Status status);
}
