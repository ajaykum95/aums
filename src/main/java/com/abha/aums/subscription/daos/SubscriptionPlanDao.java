package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;

public interface SubscriptionPlanDao {
    List<SubscriptionPlan> fetchAllSubscriptionPlanByStatus(Status status);
}
