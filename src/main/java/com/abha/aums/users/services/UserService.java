package com.abha.aums.users.services;

import com.abha.aums.users.models.User;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;

public interface UserService {
  User saveUser(User user);

  Optional<User> findByEmailAndStatusNot(String email, Status status);

  boolean verifyUser(String token);
}
