package com.example.productservice.controller;

import com.example.productservice.domain.dto.PostProductReq;
import com.example.productservice.domain.dto.PostProductRes;
import com.example.productservice.domain.dto.ProductDetailDto;
import com.example.productservice.domain.dto.GetProductListRes;
import com.example.productservice.domain.dto.StockDto;
import com.example.productservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;



    /**
     * 상품 목록을 조회하는 API
     */
    @GetMapping("")
    public ResponseEntity<List<GetProductListRes>> getProductList(
            @RequestParam(value = "type", required = false, defaultValue = "all") String type) {

        List<GetProductListRes> result = productService.getProductList(type);

        return ResponseEntity.ok().body(result);

    }

    /**
     * 상품 상세 조회 API
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDto> getProductDetailInfo(@PathVariable("productId") String productId) {

        ProductDetailDto result = productService.getProductInfo(productId);

        return ResponseEntity.ok().body(result);

    }

    /**
     * 실시간 상품 재고(수량) 조회 API
     */
    @GetMapping("/{productId}/stock")
    public ResponseEntity<StockDto> getProductStock(@PathVariable("productId") String productId){

       StockDto result =  productService.getProductStock(productId);

       return ResponseEntity.ok().body(result);

    }

//    @PostMapping("")
//    public ResponseEntity<PostProductRes> postNewProduct(@RequestBody PostProductReq postProductReq){
//
//        PostProductRes result = productService.postNewProduct(postProductReq);
//
//        return ResponseEntity.ok().body(result);
//
//    }




}
