package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.SubscriptionPlanDao;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.repositories.SubscriptionPlanRepo;
import com.abha.sharedlibrary.shared.enums.PlanType;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the {@link SubscriptionPlanDao} interface.
 * This class provides the data access logic for retrieving subscription plans based on status.
 */
@Repository
public class SubscriptionPlanDaoImpl implements SubscriptionPlanDao {

  private final SubscriptionPlanRepo subscriptionPlanRepo;

  /**
   * Constructs a new {@code SubscriptionPlanDaoImpl} with the specified repository.
   *
   * @param subscriptionPlanRepo the repository used to access subscription plan data
   */
  public SubscriptionPlanDaoImpl(SubscriptionPlanRepo subscriptionPlanRepo) {
    this.subscriptionPlanRepo = subscriptionPlanRepo;
  }

  /**
   * Retrieves all subscription plans with the specified status.
   *
   * @param status the status of the subscription plans to retrieve
   * @return a list of subscription plans matching the specified status
   */
  @Override
  public List<SubscriptionPlan> fetchAllSubscriptionPlanByStatus(Status status) {
    return subscriptionPlanRepo.findByStatus(status);
  }

  @Override
  public Optional<SubscriptionPlan> findByPlanTypeAndStatus(PlanType planType, Status status) {
    return subscriptionPlanRepo.findByPlanTypeAndStatus(planType, status);
  }
}
