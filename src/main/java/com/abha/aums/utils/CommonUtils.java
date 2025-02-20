package com.abha.aums.utils;

import com.abha.sharedlibrary.aums.request.SubscriptionUpgradeReq;
import com.abha.sharedlibrary.shared.constants.AppConstant;
import com.abha.sharedlibrary.shared.constants.HeaderConstant;
import com.abha.sharedlibrary.shared.enums.PlanCycle;
import org.springframework.http.RequestEntity;

public class CommonUtils {
  public static int getDaysByCycle(PlanCycle planCycle) {
    return switch (planCycle) {
      case MONTHLY -> 30;
      case YEARLY -> 365;
      case HALF_YEARLY -> 183;
      default -> 90;
    };
  }

  public static String getUserId(
      RequestEntity<SubscriptionUpgradeReq> subscriptionUpgradeReqEntity) {
    return subscriptionUpgradeReqEntity.getHeaders().get(HeaderConstant.USER_ID)
        .stream().findFirst().orElse("0");
  }
}
