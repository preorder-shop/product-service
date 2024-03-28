package com.example.productservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.service.annotation.GetExchange;

@AllArgsConstructor
@Getter
public class PostProductRes {
    private Long id;
    private String productId;
    private String name;

}
