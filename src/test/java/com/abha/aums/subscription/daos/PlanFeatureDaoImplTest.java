package com.abha.aums.subscription.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.abha.aums.subscription.daos.impl.PlanFeatureDaoImpl;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.repositories.PlanFeatureRepository;
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

@ExtendWith(MockitoExtension.class)
public class PlanFeatureDaoImplTest {
    @InjectMocks
    private PlanFeatureDaoImpl planFeatureDao;
    @Mock
    private PlanFeatureRepository planFeatureRepository;
    private final String UNIT = "unit";

    @Test
    @DisplayName("testing find by subscription plan and status with success case")
    @Tags({@Tag(value = UNIT), @Tag(value = "findBySubscriptionPlanAndStatus")})
    void testFindBySubscriptionPlanAndStatus_success() {
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder().id(1L).build();
        when(planFeatureRepository.findBySubscriptionPlanAndStatus(any(), any()))
                .thenReturn(Optional.of(Collections.singletonList(
                        PlanFeature.builder().id(1L).build()
                )));
        Optional<List<PlanFeature>> planFeatures = planFeatureDao.findBySubscriptionPlanAndStatus(
                subscriptionPlan, Status.ACTIVE);
        assertNotNull(planFeatures);
        assertEquals(1, planFeatures.get().size());
    }

}
