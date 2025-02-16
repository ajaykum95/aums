package com.abha.aums.subscription.daos.impl;

import com.abha.aums.subscription.daos.AppSubscriberDao;
import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AppSubscriberDaoImpl implements AppSubscriberDao {

  @Override
  public Optional<AppSubscriber> findByEmailAndStatusNot(String email, Status status) {
    return Optional.empty();
  }
}
