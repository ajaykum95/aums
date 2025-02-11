package com.abha.aums.subscription.controllers;

import com.abha.aums.subscription.services.SubscriptionPlanService;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import java.util.List;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans")
public class SubscriptionController {

    private final SubscriptionPlanService subscriptionPlanService;

    public SubscriptionController(SubscriptionPlanService subscriptionPlanService) {
        this.subscriptionPlanService = subscriptionPlanService;
    }

    @PostMapping
    public List<SubscriptionPlanResponse> fetchSubscriptionPlans(RequestEntity<?> planRequestEntity){
        return subscriptionPlanService.fetchAllActiveSubscriptionPlans();
    }

}
