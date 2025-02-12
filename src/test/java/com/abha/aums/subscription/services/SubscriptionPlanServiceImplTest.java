package com.abha.aums.subscription.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.abha.aums.subscription.daos.SubscriptionPlanDao;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.services.impl.SubscriptionPlanServiceImpl;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import com.abha.sharedlibrary.shared.enums.FeatureType;
import com.abha.sharedlibrary.shared.enums.PlanCycle;
import com.abha.sharedlibrary.shared.enums.PlanType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubscriptionPlanServiceImplTest {
    @InjectMocks
    private SubscriptionPlanServiceImpl subscriptionPlanService;
    @Mock
    private SubscriptionPlanDao subscriptionPlanDao;
    @Mock
    private PlanFeatureService planFeatureService;
    private final String UNIT = "unit";

    @Test
    @DisplayName("testing fetch all subscription plans with success case")
    @Tags({@Tag(value = UNIT), @Tag(value = "fetchAllActiveSubscriptionPlans")})
    public void testFetchAllActiveSubscriptionPlans_success() {
        when(subscriptionPlanDao.fetchAllSubscriptionPlanByStatus(any()))
                .thenReturn(getSubscriptinPlanList());
        when(planFeatureService.fetchAllPlanFeatures(any()))
                .thenReturn(getPlanFeatures());
        List<SubscriptionPlanResponse> planResponses = subscriptionPlanService.fetchAllActiveSubscriptionPlans();
        assertNotNull(planResponses);
        assertEquals(1, planResponses.size());
    }

    @Test
    @DisplayName("testing fetch all subscription plans where empty features")
    @Tags({@Tag(value = UNIT), @Tag(value = "fetchAllActiveSubscriptionPlans")})
    public void testFetchAllActiveSubscriptionPlans_emptyFeatures() {
        when(subscriptionPlanDao.fetchAllSubscriptionPlanByStatus(any()))
                .thenReturn(getSubscriptinPlanList());
        when(planFeatureService.fetchAllPlanFeatures(any()))
                .thenReturn(new ArrayList<>());
        List<SubscriptionPlanResponse> planResponses = subscriptionPlanService.fetchAllActiveSubscriptionPlans();
        assertNotNull(planResponses);
        assertEquals(1, planResponses.size());
    }

    @Test
    @DisplayName("testing fetch all subscription plans where empty plans")
    @Tags({@Tag(value = UNIT), @Tag(value = "fetchAllActiveSubscriptionPlans")})
    public void testFetchAllActiveSubscriptionPlans_emptyPlans() {
        when(subscriptionPlanDao.fetchAllSubscriptionPlanByStatus(any()))
                .thenReturn(new ArrayList<>());
        List<SubscriptionPlanResponse> planResponses = subscriptionPlanService.fetchAllActiveSubscriptionPlans();
        assertNotNull(planResponses);
        assertEquals(0, planResponses.size());
    }

    private List<PlanFeature> getPlanFeatures() {
        return Collections.singletonList(
                PlanFeature.builder()
                        .id(1L)
                        .featureType(FeatureType.PIPELINES)
                        .featureValue(12)
                        .featureDescription("12 Pipeline")
                        .build()
        );
    }

    private List<SubscriptionPlan> getSubscriptinPlanList() {
        return Collections.singletonList(
                SubscriptionPlan.builder()
                        .id(1L)
                        .planType(PlanType.BASIC)
                        .planCycle(PlanCycle.MONTHLY)
                        .description("basic plan")
                        .price(200.0)
                        .build()
        );
    }
}
