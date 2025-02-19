package com.abha.aums.subscription.services;

import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.aums.request.SubscriberDetailsRequest;
import com.abha.sharedlibrary.aums.response.SignupResponse;
import com.abha.sharedlibrary.shared.common.response.CommonResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface AppSubscriberService {
  SignupResponse registerSubscriber(RequestEntity<SignupRequest> signupRequestRequestEntity);

  CommonResponse updateSubscriberDetails(
      ResponseEntity<SubscriberDetailsRequest> subscriberDetailsReqEntity);
}
