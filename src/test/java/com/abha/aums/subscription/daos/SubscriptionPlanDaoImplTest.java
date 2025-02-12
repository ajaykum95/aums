package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.daos.impl.PlanFeatureDaoImpl;
import com.abha.aums.subscription.daos.impl.SubscriptionPlanDaoImpl;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.repositories.PlanFeatureRepository;
import com.abha.aums.subscription.repositories.SubscriptionPlanRepo;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubscriptionPlanDaoImplTest {
    @InjectMocks
    private SubscriptionPlanDaoImpl subscriptionPlanDao;
    @Mock
    private SubscriptionPlanRepo subscriptionPlanRepo;
    private final String UNIT = "unit";

    @Test
    @DisplayName("testing find by subscription plan and status with success case")
    @Tags({@Tag(value = UNIT), @Tag(value = "findBySubscriptionPlanAndStatus")})
    void testFindBySubscriptionPlanAndStatus_success() {
        when(subscriptionPlanRepo.findByStatus(any()))
                .thenReturn(Collections.singletonList(
                        SubscriptionPlan.builder()
                                .id(1L)
                                .build()
                ));
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanDao.fetchAllSubscriptionPlanByStatus(
                Status.ACTIVE);
        assertNotNull(subscriptionPlans);
        assertEquals(1, subscriptionPlans.size());
    }

}
