package com.abha.aums.integration.pams;

import com.abha.sharedlibrary.pams.enums.EntityType;
import com.abha.sharedlibrary.pams.request.PaymentInfoRequest;
import com.abha.sharedlibrary.pams.response.PaymentInfo;
import com.abha.sharedlibrary.pams.response.PaymentInfoResponse;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
  @Override
  public PaymentInfoResponse fetchPaymentDetails(
      PaymentInfoRequest paymentInfoRequest, HttpHeaders headers) {
    //TODO
    return PaymentInfoResponse.builder()
        .id(1L)
        .subscriptionId(1L)
        .paymentInfo(Collections.singletonList(
            PaymentInfo.builder()
                .entityId(1L)
                .entityType(EntityType.SUBSCRIPTION_PLAN)
                .amount(100)
                .build()
        ))
        .build();
  }
}
