package com.abha.aums.subscription.repositories;

import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanFeatureRepository extends JpaRepository<PlanFeature, Long> {
    Optional<List<PlanFeature>> findBySubscriptionPlanAndStatus(
            SubscriptionPlan subscriptionPlan, Status status);
}
