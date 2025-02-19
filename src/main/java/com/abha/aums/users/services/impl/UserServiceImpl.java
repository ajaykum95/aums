package com.abha.aums.users.services.impl;

import com.abha.aums.exceptions.AbhaExceptions;
import com.abha.aums.users.daos.UserDao;
import com.abha.aums.users.models.User;
import com.abha.aums.users.services.UserService;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.Optional;
import org.springframework.stereotype.Service;

import static com.abha.sharedlibrary.shared.common.ExceptionUtil.buildException;

@Service
public class UserServiceImpl implements UserService {
  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public User saveUser(User user) {
    return userDao.saveUser(user);
  }

  @Override
  public Optional<User> findByEmailAndStatusNot(String email, Status status) {
    return userDao.findByEmailAndStatusNot(email, status);
  }

  @Override
  public boolean verifyUser(String token) {
    User user = userDao.findByToken(token)
        .orElseThrow(() -> buildException(AbhaExceptions.INVALID_VERIFICATION_URL));
    user.setEmailVerified(true);
    userDao.updateUser(user);
    return true;
  }
}
