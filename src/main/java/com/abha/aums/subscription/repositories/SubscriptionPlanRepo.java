package com.abha.aums.subscription.repositories;

import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link SubscriptionPlan} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations.
 */
public interface SubscriptionPlanRepo extends JpaRepository<SubscriptionPlan, Long> {
  /**
   * Retrieves a list of subscription plans based on their status.
   *
   * @param status the status of the subscription plans to retrieve
   * @return a list of subscription plans matching the specified status
   */
  List<SubscriptionPlan> findByStatus(Status status);
}
