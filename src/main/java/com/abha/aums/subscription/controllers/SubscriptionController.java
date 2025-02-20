package com.abha.aums.subscription.controllers;

import com.abha.aums.subscription.services.AppSubscriptionService;
import com.abha.aums.utils.RequestValidator;
import com.abha.sharedlibrary.aums.request.SubscriptionUpgradeReq;
import com.abha.sharedlibrary.shared.common.response.CommonResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

  private final AppSubscriptionService appSubscriptionService;

  public SubscriptionController(AppSubscriptionService appSubscriptionService) {
    this.appSubscriptionService = appSubscriptionService;
  }

  @PostMapping("/upgrade")
  public ResponseEntity<CommonResponse> upgradeSubscription(
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity) {
    RequestValidator.validatePlanUpgradeReq(subscriptionUpgradeReqEntity);
    return ResponseEntity.ok(appSubscriptionService.upgradeSubscriptionPlan(
        subscriptionUpgradeReqEntity));
  }
}
