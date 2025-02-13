package com.abha.aums.subscription.services;

import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import java.util.List;

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
}
