package com.abha.aums.subscription.repositories;

import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link PlanFeature} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations.
 */
public interface PlanFeatureRepository extends JpaRepository<PlanFeature, Long> {
  /**
   * Retrieves a list of plan features associated with a given subscription plan and status.
   *
   * @param subscriptionPlan the subscription plan to filter features by
   * @param status           the status of the plan features to retrieve
   * @return an optional list of plan features matching the specified subscription plan and status
   */
  Optional<List<PlanFeature>> findBySubscriptionPlanAndStatus(
      SubscriptionPlan subscriptionPlan, Status status);
}
