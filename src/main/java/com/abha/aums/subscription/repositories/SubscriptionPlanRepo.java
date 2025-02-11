package com.abha.aums.subscription.repositories;

import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepo extends JpaRepository<SubscriptionPlan, Long> {
    List<SubscriptionPlan> findByStatus(Status status);
}
