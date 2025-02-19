package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.AppSubscriberDao;
import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.users.repositories.AppSubscriberRepo;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AppSubscriberDaoImpl implements AppSubscriberDao {

  private final AppSubscriberRepo appSubscriberRepo;

  public AppSubscriberDaoImpl(AppSubscriberRepo appSubscriberRepo) {
    this.appSubscriberRepo = appSubscriberRepo;
  }

  @Override
  public Optional<AppSubscriber> findByEmailAndStatusNot(String email, Status status) {
    return appSubscriberRepo.findByEmailAndStatusNot(email, status);
  }

  @Override
  public AppSubscriber saveAppSubscriber(AppSubscriber appSubscriber) {
    return appSubscriberRepo.save(appSubscriber);
  }

  @Override
  public Optional<AppSubscriber> getAppSubscriberById(Long appSubscriberId) {
    return appSubscriberRepo.findById(appSubscriberId);
  }
}
