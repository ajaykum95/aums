package com.abha.aums.integration.pams;

import com.abha.sharedlibrary.pams.request.PaymentInfoRequest;
import com.abha.sharedlibrary.pams.response.PaymentInfoResponse;
import org.springframework.http.HttpHeaders;

public interface PaymentService {
  PaymentInfoResponse fetchPaymentDetails(
      PaymentInfoRequest paymentInfoRequest, HttpHeaders headers);
}
