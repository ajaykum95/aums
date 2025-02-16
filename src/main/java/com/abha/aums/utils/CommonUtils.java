package com.abha.aums.utils;

import com.abha.sharedlibrary.shared.enums.PlanCycle;

public class CommonUtils {
  public static int getDaysByCycle(PlanCycle planCycle) {
    return switch (planCycle) {
      case MONTHLY -> 30;
      case YEARLY -> 365;
      case HALF_YEARLY -> 183;
      default -> 90;
    };
  }
}
