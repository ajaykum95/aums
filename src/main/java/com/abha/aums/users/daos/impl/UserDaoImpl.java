package com.abha.aums.users.daos.impl;

import com.abha.aums.users.daos.UserDao;
import com.abha.aums.users.models.User;
import com.abha.aums.users.repositories.UserRepository;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

  private final UserRepository userRepository;

  public UserDaoImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<User> findByEmailAndStatusNot(String email, Status status) {
    return userRepository.findByEmailAndStatusNot(email, status);
  }

  @Override
  public Optional<User> findByToken(String token) {
    return userRepository.findByToken(token);
  }

  @Override
  public void updateUser(User user) {
    userRepository.save(user);
  }
}
