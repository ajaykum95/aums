package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;

/**
 * Data Access Object (DAO) interface for managing subscription plans.
 * This interface provides methods to retrieve subscription plans based on their status.
 */
public interface SubscriptionPlanDao {

  /**
   * Fetches all subscription plans with the given status.
   *
   * @param status the status of the subscription plans to retrieve
   * @return a list of subscription plans matching the specified status
   */
  List<SubscriptionPlan> fetchAllSubscriptionPlanByStatus(Status status);
}
