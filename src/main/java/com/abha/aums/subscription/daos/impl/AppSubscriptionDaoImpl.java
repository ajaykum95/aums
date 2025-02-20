package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.AppSubscriptionDao;
import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.subscription.repositories.AppSubscriptionRepo;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
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

  @Override
  public AppSubscriptions getActiveSubscriptionPlan(AppSubscriber appSubscriber) {
    return appSubscriptionRepo.findByAppSubscriberAndStatus(appSubscriber, Status.ACTIVE)
        .orElse(null);
  }

  @Override
  public void saveAllSubscription(List<AppSubscriptions> appSubscriptions) {
    appSubscriptionRepo.saveAll(appSubscriptions);
  }
}
