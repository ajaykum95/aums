package com.abha.aums.controllers;

import com.abha.sharedlibrary.aums.request.UserRequest;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plans")
public class SubscriptionController {

    @GetMapping
    public List<SubscriptionPlanResponse> fetchSubscriptionPlans(){
        return Collections.singletonList(
                SubscriptionPlanResponse.builder()
                        .name("Basic")
                        .build()
        );
    }

}
