package com.abha.aums.subscription.daos;

import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;

public interface AppSubscriberDao {
  Optional<AppSubscriber> findByEmailAndStatusNot(String email, Status status);

  AppSubscriber saveAppSubscriber(AppSubscriber appSubscriber);

  AppSubscriber getAppSubscriberById(Long appSubscriberId);

  AppSubscriber findByIdAndStatusNot(Long subscriberId, Status status);
}
