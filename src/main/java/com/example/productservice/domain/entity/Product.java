package com.example.productservice.domain.entity;

import com.example.productservice.domain.BaseEntity;
import com.example.productservice.domain.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // pk

    @Column(nullable = false,name = "product_id")
    private String productId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "product_type")
    private ProductType productType; // 상품 종류 (일반 상품, 예약 상품)

    @Column(nullable = false)
    private int price; // 가격


    @Builder
    private Product(String productId, String name, ProductType productType, int price){
        this.productId = productId;
        this.name = name;
        this.productType = productType;
        this.price = price;
    }




}
