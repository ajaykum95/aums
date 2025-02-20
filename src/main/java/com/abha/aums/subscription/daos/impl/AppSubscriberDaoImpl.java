package com.abha.aums.subscription.daos.impl;

import com.abha.aums.exceptions.AbhaExceptions;
import com.abha.aums.subscription.daos.AppSubscriberDao;
import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.users.repositories.AppSubscriberRepo;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import static com.abha.sharedlibrary.shared.common.ExceptionUtil.buildException;

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
  public AppSubscriber getAppSubscriberById(Long appSubscriberId) {
    return appSubscriberRepo.findById(appSubscriberId)
        .orElseThrow(() -> buildException(AbhaExceptions.APP_SUBSCRIBER_NOT_FOUND));
  }

  @Override
  public AppSubscriber findByIdAndStatusNot(Long subscriberId, Status status) {
    return appSubscriberRepo.findByIdAndStatusNot(subscriberId, status)
        .orElseThrow(() -> buildException(AbhaExceptions.APP_SUBSCRIBER_NOT_FOUND));
  }
}
