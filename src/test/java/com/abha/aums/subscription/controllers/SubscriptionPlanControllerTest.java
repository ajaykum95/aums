package com.abha.aums.subscription.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.abha.aums.subscription.services.SubscriptionPlanService;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import com.abha.sharedlibrary.shared.constants.HeaderConstant;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

@ExtendWith(MockitoExtension.class)
public class SubscriptionPlanControllerTest {
  @InjectMocks
  private SubscriptionPlanController subscriptionPlanController;

  @Mock
  private SubscriptionPlanService subscriptionPlanService;
  private final String UNIT = "unit";

  @Test
  @DisplayName("testing fetch subscription plans with success case")
  @Tags({@Tag(value = UNIT), @Tag(value = "fetchSubscriptionPlans_s")})
  void testFetchSubscriptionPlans_success() {
    Map<String, String> headers =  new HashMap<>();
    headers.put(HeaderConstant.USER_ID, "Ajay");
    headers.put(HeaderConstant.CLIENT_ID, "aums");
    RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.POST,
        URI.create("/test"));
    when(subscriptionPlanService.fetchAllActiveSubscriptionPlans()).
        thenReturn(Collections.singletonList(SubscriptionPlanResponse.builder().id(1L).build()));
    List<SubscriptionPlanResponse> subscriptionPlanResponses =
        subscriptionPlanController.fetchSubscriptionPlans(requestEntity);
    assertNotNull(subscriptionPlanResponses);
    assertEquals(1, subscriptionPlanResponses.size());
  }
}
