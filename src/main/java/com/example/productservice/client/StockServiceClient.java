package com.example.productservice.client;

import com.example.productservice.domain.dto.PostStockReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stockService" )
public interface StockServiceClient {

    // todo : 특정 삼품의 재고 수량을 가지고 오는 api

    // todo : 상품 재고 등록
    @PostMapping("/new")
    public void postStock(@RequestBody PostStockReq postStockReq);
}
