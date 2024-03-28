package com.example.productservice.domain.dto;

import com.example.productservice.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
public class PostStockReq {
    private String productId;
    private ProductType productType;
    private int stock;
}
