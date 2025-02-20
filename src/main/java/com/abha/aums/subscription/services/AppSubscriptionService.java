package com.abha.aums.subscription.services;

import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.sharedlibrary.aums.request.SubscriptionUpgradeReq;
import com.abha.sharedlibrary.shared.common.response.CommonResponse;
import org.springframework.http.RequestEntity;

public interface AppSubscriptionService {
  AppSubscriptions saveAppSubscription(AppSubscriptions appSubscriptions);

  CommonResponse upgradeSubscriptionPlan(
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity);
}
