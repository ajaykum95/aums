package com.abha.aums.integration.enms;

import com.abha.aums.users.models.User;
import com.abha.sharedlibrary.enms.request.SendNotificationRequest;

public interface NotificationService {
  void sendEmailVerificationMail(SendNotificationRequest sendNotificationRequest);
}
