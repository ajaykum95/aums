package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.PlanFeatureDao;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.repositories.PlanFeatureRepository;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the {@link PlanFeatureDao} interface.
 * This class provides data access logic for retrieving plan features
 * based on subscription plans and status.
 */
@Repository
public class PlanFeatureDaoImpl implements PlanFeatureDao {

  private final PlanFeatureRepository planFeatureRepository;

  /**
   * Constructs a new {@code PlanFeatureDaoImpl} with the specified repository.
   *
   * @param planFeatureRepository the repository used to access plan feature data
   */
  public PlanFeatureDaoImpl(PlanFeatureRepository planFeatureRepository) {
    this.planFeatureRepository = planFeatureRepository;
  }

  /**
   * Retrieves a list of plan features associated with a given subscription plan and status.
   *
   * @param subscriptionPlan the subscription plan to filter features by
   * @param status           the status of the plan features to retrieve
   * @return an optional list of plan features matching the specified subscription plan and status
   */
  @Override
  public Optional<List<PlanFeature>> findBySubscriptionPlanAndStatus(
      SubscriptionPlan subscriptionPlan, Status status) {
    return planFeatureRepository.findBySubscriptionPlanAndStatus(subscriptionPlan, status);
  }
}
