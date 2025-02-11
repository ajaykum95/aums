package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.SubscriptionPlanDao;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.repositories.SubscriptionPlanRepo;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionPlanDaoImpl implements SubscriptionPlanDao {

    private final SubscriptionPlanRepo subscriptionPlanRepo;

    public SubscriptionPlanDaoImpl(SubscriptionPlanRepo subscriptionPlanRepo) {
        this.subscriptionPlanRepo = subscriptionPlanRepo;
    }

    @Override
    public List<SubscriptionPlan> fetchAllSubscriptionPlanByStatus(Status status) {
        return subscriptionPlanRepo.findByStatus(status);
    }
}
