package com.abha.aums.subscription.repositories;

import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSubscriptionRepo extends JpaRepository<AppSubscriptions, Long> {
  Optional<AppSubscriptions> findByAppSubscriberAndStatus(AppSubscriber appSubscriber, Status status);
}
