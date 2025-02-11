package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.PlanFeatureDao;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.repositories.PlanFeatureRepository;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PlanFeatureDaoImpl implements PlanFeatureDao {

    private final PlanFeatureRepository planFeatureRepository;

    public PlanFeatureDaoImpl(PlanFeatureRepository planFeatureRepository) {
        this.planFeatureRepository = planFeatureRepository;
    }

    @Override
    public Optional<List<PlanFeature>> findBySubscriptionPlanAndStatus(
            SubscriptionPlan subscriptionPlan, Status status) {
        return planFeatureRepository.findBySubscriptionPlanAndStatus(subscriptionPlan, status);
    }
}
