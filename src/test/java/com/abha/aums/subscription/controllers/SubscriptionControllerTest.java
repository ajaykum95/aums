package com.abha.aums.subscription.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

@ExtendWith(MockitoExtension.class)
public class SubscriptionControllerTest {
  @InjectMocks
  private SubscriptionController subscriptionController;

  @Test
  void testFetchSubscriptionPlans_success() {
    Map<String, String> headers =  new HashMap<>();
    headers.put(HeaderCon.USER_ID, "Ajay");
    headers.put(HeaderConstant.CLIENT_ID, "aums");
    RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.POST,
        URI.create("/test"));

    subscriptionController.fetchSubscriptionPlans(requestEntity);
  }
}
