package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Optional;

public interface PlanFeatureDao {
    Optional<List<PlanFeature>> findBySubscriptionPlanAndStatus(
            SubscriptionPlan subscriptionPlan, Status status);
}
