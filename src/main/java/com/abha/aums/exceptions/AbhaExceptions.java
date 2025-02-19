package com.abha.aums.exceptions;

import com.abha.sharedlibrary.exceptions.BusinessExceptionDefintion;
import com.abha.sharedlibrary.exceptions.ErrorCategory;
import com.abha.sharedlibrary.exceptions.ExceptionTypes;

/**
 * This Enum defines the Exceptions for IoMS.
 */
public enum AbhaExceptions implements BusinessExceptionDefintion {
  DB_ERROR(1000, ErrorCategory.ERROR, ExceptionTypes.DB_ERROR, "%s"),
  SIGNUP_REQUEST_MISSING(1001, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Signup request missing!"),
  FIRST_NAME_MISSING(1002, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "First name is mandatory!"),
  INVALID_EMAIL_ADDRESS(1003, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Invalid email address!"),
  INVALID_PASSWORD(1004, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Invalid password, Minimum length should be 6!"),
  ACCOUNT_ALREADY_PRESENT(1005, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Account already exist!"),
  TRIAL_PLAN_MISSING(1006, ErrorCategory.ERROR, ExceptionTypes.INTERNAL_ERROR,
      "Trial plan not found!"),
  USER_ACCOUNT_ALREADY_PRESENT(1007, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
      "User account already present!"),
  INVALID_VERIFICATION_URL(1008, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
      "Invalid verification URL!"),
  SUBS_UPDATE_DETAILS_MISSING(1009, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Subscription details missing!"),
  PHONE_NUMBER_MISSING(1010, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Phone number is missing!"),
  REFERENCE_MISSING(1011, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Reference is missing!"),
  COMPANY_NAME_MISSING(1012, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Company name is missing!"),
  COMPANY_SIZE_MISSING(1013, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Company size is missing!"),
  SALES_TEAM_SIZE_MISSING(1014, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Sales team size is missing!"),
  INDUSTRY_MISSING(1015, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "Industry type is missing!"),
  CRM_PRIORITIES_MISSING(1016, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "CRM Properties is missing!"),
  APP_SUBSCRIBER_ID_MISSING(1017, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
      "App subscriber id is missing!"),
  APP_SUBSCRIBER_NOT_FOUND(1018, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
      "App subscriber not found!");

  private final int businessErrorCode;
  private final ErrorCategory errorCategory;
  private final ExceptionTypes exceptionType;
  private final String errorMessage;

  AbhaExceptions(int businessErrorCode, ErrorCategory errorCategory,
                 ExceptionTypes exceptionType, String errorMessage) {
    this.businessErrorCode = businessErrorCode;
    this.errorCategory = errorCategory;
    this.exceptionType = exceptionType;
    this.errorMessage = errorMessage;
  }

  @Override
  public int getBusinessErrorCode() {
    return this.businessErrorCode;
  }

  @Override
  public ErrorCategory getErrorCategory() {
    return this.errorCategory;
  }

  @Override
  public ExceptionTypes getExceptionType() {
    return this.exceptionType;
  }

  @Override
  public String getErrorMessage() {
    return this.errorMessage;
  }
}
