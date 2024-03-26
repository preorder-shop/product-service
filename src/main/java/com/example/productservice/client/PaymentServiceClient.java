package com.example.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "paymentService" ,url = "localhost:8084/payments")
public interface PaymentServiceClient {


}
