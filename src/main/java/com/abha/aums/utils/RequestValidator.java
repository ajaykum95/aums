package com.abha.aums.utils;

import com.abha.aums.exceptions.AbhaExceptions;
import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.aums.request.SubscriberDetailsRequest;
import com.abha.sharedlibrary.shared.validator.EmailValidator;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import static com.abha.sharedlibrary.shared.common.ExceptionUtil.buildException;

public class RequestValidator {
  public static void validateSignupRequest(RequestEntity<SignupRequest> signupRequestRequestEntity) {
    if (Objects.isNull(signupRequestRequestEntity)) {
      throw buildException(AbhaExceptions.SIGNUP_REQUEST_MISSING);
    }
    SignupRequest signupRequest = signupRequestRequestEntity.getBody();
    if (StringUtils.isEmpty(signupRequest.getFirstName())) {
      throw buildException(AbhaExceptions.FIRST_NAME_MISSING);
    }
    if (!EmailValidator.isValidEmail(signupRequest.getEmail())) {
      throw buildException(AbhaExceptions.INVALID_EMAIL_ADDRESS);
    }
    if (StringUtils.isEmpty(signupRequest.getPassword())
        || signupRequest.getPassword().length() <= 6) {
      throw buildException(AbhaExceptions.INVALID_PASSWORD);
    }
  }

  public static void validateSubscriberDetailsReq(
      ResponseEntity<SubscriberDetailsRequest> subscriberDetailsReqEntity) {
    if (Objects.isNull(subscriberDetailsReqEntity)) {
      throw buildException(AbhaExceptions.SUBS_UPDATE_DETAILS_MISSING);
    }
    SubscriberDetailsRequest subscriberDetailsRequest = subscriberDetailsReqEntity.getBody();
    if (Objects.isNull(subscriberDetailsRequest.getAppSubscriberId())) {
      throw buildException(AbhaExceptions.APP_SUBSCRIBER_ID_MISSING);
    }
    if (StringUtils.isEmpty(subscriberDetailsRequest.getPhone())) {
      throw buildException(AbhaExceptions.PHONE_NUMBER_MISSING);
    }
    if (StringUtils.isEmpty(subscriberDetailsRequest.getEntityReference())) {
      throw buildException(AbhaExceptions.REFERENCE_MISSING);
    }
    if (StringUtils.isEmpty(subscriberDetailsRequest.getCompanyName())) {
      throw buildException(AbhaExceptions.COMPANY_NAME_MISSING);
    }
    if (StringUtils.isEmpty(subscriberDetailsRequest.getCompanySize())) {
      throw buildException(AbhaExceptions.COMPANY_SIZE_MISSING);
    }
    if (StringUtils.isEmpty(subscriberDetailsRequest.getSalesTeamSize())) {
      throw buildException(AbhaExceptions.SALES_TEAM_SIZE_MISSING);
    }
    if (Objects.isNull(subscriberDetailsRequest.getIndustry())) {
      throw buildException(AbhaExceptions.INDUSTRY_MISSING);
    }
    if (CollectionUtils.isEmpty(subscriberDetailsRequest.getCrmPriorityIds())) {
      throw buildException(AbhaExceptions.CRM_PRIORITIES_MISSING);
    }
  }
}
