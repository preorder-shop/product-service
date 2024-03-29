package com.example.productservice.client;

import com.example.productservice.domain.dto.PostStockReq;
import com.example.productservice.domain.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stockService" ,url = "http://localhost:8085")
public interface StockServiceClient {

    @PostMapping("/internal/stocks/new")
    void postStock(@RequestBody PostStockReq postStockReq);


    @GetMapping("/internal/stocks/{productId}")
    StockDto getProductStock(@PathVariable("productId") String productId);
}
