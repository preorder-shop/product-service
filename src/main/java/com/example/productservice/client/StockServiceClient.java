package com.example.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stockService" )
public interface StockServiceClient {

    // todo : 특정 삼품의 재고 수량을 가지고 오는 api
}
