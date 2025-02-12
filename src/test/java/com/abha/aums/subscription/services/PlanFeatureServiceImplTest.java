package com.abha.aums.subscription.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.abha.aums.subscription.daos.PlanFeatureDao;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.services.impl.PlanFeatureServiceImpl;
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
public class PlanFeatureServiceImplTest {
    @InjectMocks
    private PlanFeatureServiceImpl planFeatureService;
    @Mock
    private PlanFeatureDao planFeatureDao;
    private final String UNIT = "unit";

    @Test
    @DisplayName("testing fetch all plan features with success case")
    @Tags({@Tag(value = UNIT), @Tag(value = "fetchAllPlanFeatures")})
    public void testFetchAllPlanFeatures_success() {
        SubscriptionPlan subscriptionPlan = SubscriptionPlan.builder().id(1L).build();
        when(planFeatureDao.findBySubscriptionPlanAndStatus(any(), any()))
                .thenReturn(Optional.of(Collections.singletonList(PlanFeature.builder().id(1L).build())));
        List<PlanFeature> planFeatures = planFeatureService.fetchAllPlanFeatures(subscriptionPlan);
        assertNotNull(planFeatures);
        assertEquals(1, planFeatures.size());
    }
}
