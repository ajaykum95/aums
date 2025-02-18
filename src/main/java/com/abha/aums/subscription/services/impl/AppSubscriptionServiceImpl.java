package com.abha.aums.subscription.services.impl;

import com.abha.aums.subscription.daos.AppSubscriptionDao;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.subscription.services.AppSubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class AppSubscriptionServiceImpl implements AppSubscriptionService {

  private final AppSubscriptionDao appSubscriptionDao;

  public AppSubscriptionServiceImpl(AppSubscriptionDao appSubscriptionDao) {
    this.appSubscriptionDao = appSubscriptionDao;
  }

  @Override
  public AppSubscriptions saveAppSubscription(AppSubscriptions appSubscriptions) {
    return appSubscriptionDao.saveAppSubscription(appSubscriptions);
  }
}
