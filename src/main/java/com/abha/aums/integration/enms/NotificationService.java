package com.abha.aums.integration.enms;

import com.abha.aums.users.models.User;

public interface NotificationService {
  void sendEmailVerificationMail(User savedUser);
}
