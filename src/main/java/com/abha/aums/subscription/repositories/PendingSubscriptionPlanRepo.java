package com.abha.aums.subscription.repositories;

import com.abha.aums.subscription.models.PendingSubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingSubscriptionPlanRepo extends JpaRepository<PendingSubscriptionPlan, Long> {
}
