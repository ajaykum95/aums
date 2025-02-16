package com.abha.aums.subscription.services;

import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.aums.response.SignupResponse;
import org.springframework.http.RequestEntity;

public interface AppSubscriberService {
  SignupResponse registerSubscriber(RequestEntity<SignupRequest> signupRequestRequestEntity);
}
