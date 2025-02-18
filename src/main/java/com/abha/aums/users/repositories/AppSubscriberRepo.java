package com.abha.aums.users.repositories;

import com.abha.aums.subscription.models.AppSubscriber;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSubscriberRepo extends JpaRepository<AppSubscriber, Long> {
  Optional<AppSubscriber> findByEmailAndStatusNot(String email, Status status);
}
