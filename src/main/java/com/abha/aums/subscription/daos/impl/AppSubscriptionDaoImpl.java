package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.AppSubscriptionDao;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.subscription.repositories.AppSubscriptionRepo;
import org.springframework.stereotype.Repository;

@Repository
public class AppSubscriptionDaoImpl implements AppSubscriptionDao {

  private final AppSubscriptionRepo appSubscriptionRepo;

  public AppSubscriptionDaoImpl(AppSubscriptionRepo appSubscriptionRepo) {
    this.appSubscriptionRepo = appSubscriptionRepo;
  }

  @Override
  public AppSubscriptions saveAppSubscription(AppSubscriptions appSubscriptions) {
    return appSubscriptionRepo.save(appSubscriptions);
  }
}
