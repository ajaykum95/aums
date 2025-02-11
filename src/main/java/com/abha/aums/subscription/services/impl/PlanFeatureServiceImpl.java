package com.abha.aums.subscription.services.impl;

import com.abha.aums.subscription.daos.PlanFeatureDao;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.services.PlanFeatureService;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlanFeatureServiceImpl implements PlanFeatureService {
    private final PlanFeatureDao planFeatureDao;

    public PlanFeatureServiceImpl(PlanFeatureDao planFeatureDao) {
        this.planFeatureDao = planFeatureDao;
    }

    @Override
    public List<PlanFeature> fetchAllPlanFeatures(SubscriptionPlan subscriptionPlan) {
        return planFeatureDao.findBySubscriptionPlanAndStatus(subscriptionPlan, Status.ACTIVE)
                .orElse(new ArrayList<>());
    }
}
