package com.abha.aums.users.controllers;

import com.abha.aums.subscription.services.AppSubscriberService;
import com.abha.aums.utils.RequestValidator;
import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.aums.request.SubscriberDetailsRequest;
import com.abha.sharedlibrary.aums.response.SignupResponse;
import com.abha.sharedlibrary.shared.common.response.CommonResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriber")
public class SubscriberController {

  private final AppSubscriberService appSubscriberService;

  public SubscriberController(AppSubscriberService appSubscriberService) {
    this.appSubscriberService = appSubscriberService;
  }

  @PostMapping("/signup")
  public ResponseEntity<SignupResponse> signupSubscriber(RequestEntity<SignupRequest> signupRequestRequestEntity) {
    RequestValidator.validateSignupRequest(signupRequestRequestEntity);
    return ResponseEntity.ok(appSubscriberService.registerSubscriber(signupRequestRequestEntity));
  }

  @PutMapping("/update-details")
  public ResponseEntity<CommonResponse> updateDetails(
      ResponseEntity<SubscriberDetailsRequest> subscriberDetailsReqEntity) {
    RequestValidator.validateSubscriberDetailsReq(subscriberDetailsReqEntity);
    return ResponseEntity.ok(appSubscriberService.updateSubscriberDetails(subscriberDetailsReqEntity));
  }

}
