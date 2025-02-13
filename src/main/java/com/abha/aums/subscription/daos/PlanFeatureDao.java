package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Optional;

/**
 * Data Access Object (DAO) interface for managing plan features.
 * This interface provides methods to retrieve plan features based on subscription
 * plans and their status.
 */
public interface PlanFeatureDao {
  /**
   * Finds plan features by the given subscription plan and status.
   *
   * @param subscriptionPlan the subscription plan associated with the features
   * @param status           the status of the plan features to retrieve
   * @return an optional list of plan features matching the specified criteria
   */
  Optional<List<PlanFeature>> findBySubscriptionPlanAndStatus(
      SubscriptionPlan subscriptionPlan, Status status);
}
