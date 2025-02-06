package com.abha.aums.subscription.controllers;

import com.abha.sharedlibrary.aums.request.UserRequest;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import java.util.Collections;
import java.util.List;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class SubscriptionController {

    @PostMapping
    public List<SubscriptionPlanResponse> fetchSubscriptionPlans(
            RequestEntity<UserRequest> planRequestEntity){
        return Collections.singletonList(
                SubscriptionPlanResponse.builder()
                        .name("Basic")
                        .build()
        );
    }

}
