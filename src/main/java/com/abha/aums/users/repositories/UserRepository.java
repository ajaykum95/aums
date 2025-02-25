package com.abha.aums.users.repositories;

import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.aums.users.models.User;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailAndStatusNot(String email, Status status);

  Optional<User> findByToken(String token);

  Optional<List<User>> findByAppSubscriptionsAndStatusNot(
      AppSubscriptions appSubscriptions, Status status);
}
