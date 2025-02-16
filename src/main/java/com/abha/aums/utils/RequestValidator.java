package com.abha.aums.utils;

import com.abha.aums.exceptions.AbhaExceptions;
import com.abha.sharedlibrary.aums.request.SignupRequest;
import com.abha.sharedlibrary.shared.validator.EmailValidator;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.RequestEntity;

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
}
