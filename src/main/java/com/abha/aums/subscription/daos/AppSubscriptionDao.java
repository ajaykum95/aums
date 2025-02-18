package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.models.AppSubscriptions;

public interface AppSubscriptionDao {
  AppSubscriptions saveAppSubscription(AppSubscriptions appSubscriptions);
}
