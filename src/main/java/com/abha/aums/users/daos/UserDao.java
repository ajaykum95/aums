package com.abha.aums.users.daos;

import com.abha.aums.users.models.User;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;

public interface UserDao {
  User saveUser(User user);

  Optional<User> findByEmailAndStatusNot(String email, Status status);

  Optional<User> findByToken(String token);

  void updateUser(User user);
}
