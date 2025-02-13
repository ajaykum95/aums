package com.abha.aums.subscription.controllers;

import com.abha.aums.subscription.services.SubscriptionPlanService;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import java.util.List;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling subscription plan-related requests.
 * This class exposes APIs to fetch subscription plans.
 */
@RestController
@RequestMapping("/api/plans")
public class SubscriptionPlanController {

  private final SubscriptionPlanService subscriptionPlanService;

  /**
   * Constructor to initialize the SubscriptionPlanController with the required service.
   *
   * @param subscriptionPlanService the service responsible for handling subscription plans
   */
  public SubscriptionPlanController(SubscriptionPlanService subscriptionPlanService) {
    this.subscriptionPlanService = subscriptionPlanService;
  }

  /**
   * Fetches all active subscription plans.
   *
   * @param planRequestEntity the request entity containing request details
   * @return a list of active subscription plans
   */
  @PostMapping
  public List<SubscriptionPlanResponse> fetchSubscriptionPlans(
      RequestEntity<?> planRequestEntity) {
    return subscriptionPlanService.fetchAllActiveSubscriptionPlans();
  }

}
