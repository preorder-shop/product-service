package com.example.productservice.domain.dto;

import com.example.productservice.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostProductReq {
    private String productId;
    private String name;
    private ProductType productType;
    private int stock;

}
