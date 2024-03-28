package com.example.productservice.client;

import com.example.productservice.domain.dto.PostStockReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stockService" )
public interface StockServiceClient {

    @PostMapping("/new")
    void postStock(@RequestBody PostStockReq postStockReq);
}
