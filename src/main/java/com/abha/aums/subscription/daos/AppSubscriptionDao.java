package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.subscription.models.AppSubscriptions;
import java.util.List;

public interface AppSubscriptionDao {
  AppSubscriptions saveAppSubscription(AppSubscriptions appSubscriptions);

  AppSubscriptions getActiveSubscriptionPlan(AppSubscriber appSubscriber);

  void saveAllSubscription(List<AppSubscriptions> appSubscriptions);
}
