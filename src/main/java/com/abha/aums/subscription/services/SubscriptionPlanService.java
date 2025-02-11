package com.abha.aums.subscription.services;

import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import java.util.List;

public interface SubscriptionPlanService {
    List<SubscriptionPlanResponse> fetchAllActiveSubscriptionPlans();
}
