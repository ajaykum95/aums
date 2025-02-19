package com.abha.aums.integration.enms;

import com.abha.sharedlibrary.enms.request.SendNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
  @Override
  public Integer sendEmailVerificationMail(SendNotificationRequest sendNotificationRequest) {
    //TODO CALL ENMS TO SEND NOTIFICATION
    log.info("Requested ENMS to send email notification {}", sendNotificationRequest);
    return 0;
  }
}
