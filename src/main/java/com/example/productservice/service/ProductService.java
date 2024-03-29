package com.example.productservice.service;


import static com.example.productservice.common.response.BaseResponseStatus.PRODUCT_NOT_FOUND;

import com.example.productservice.client.StockServiceClient;
import com.example.productservice.common.exceptions.BaseException;
import com.example.productservice.domain.ProductType;
import com.example.productservice.domain.dto.PostProductReq;
import com.example.productservice.domain.dto.PostProductRes;
import com.example.productservice.domain.dto.ProductDetailDto;
import com.example.productservice.domain.dto.GetProductListRes;
import com.example.productservice.domain.dto.StockDto;
import com.example.productservice.domain.entity.Product;
import com.example.productservice.repository.ProductRedisRepository;
import com.example.productservice.repository.ProductRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductRedisRepository productRedisRepository;

    private final StockServiceClient stockServiceClient;


    public List<GetProductListRes> getProductList(String type) {
        List<Product> products = null;

        if (type.equals("all")) {
            products = productRepository.findAll();
        }
        if (type.equals("reservation")) {
            products = productRepository.findALlByProductType(ProductType.RESERVATION);
        }
        if (type.equals("general")) {
            products = productRepository.findALlByProductType(ProductType.GENERAL);
        }
        if (products == null) {
            throw new IllegalArgumentException("DB 에러 발생");
        }

        return products.stream().map(product -> GetProductListRes.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .productType(product.getProductType().toString())
                .build()
        ).collect(Collectors.toList());
    }


    public ProductDetailDto getProductInfo(String productId) {

        Map<String, Object> product = productRedisRepository.getProduct(productId);
        log.info("look redis");
        log.info("product={}", product);

        if (product.isEmpty()) {
            log.info("redis is empty, look db");
            Product productInDB = productRepository.findByProductId(productId)
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 상품 번호 입니다."));
            saveCache(productId, productInDB);

            product = productRedisRepository.getProduct(productId);

        }

        return parsing(productId, product);

    }


    private void saveCache(String productId, Product product) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("type", product.getProductType().toString());
        map.put("price", String.valueOf(product.getPrice()));
        productRedisRepository.saveProduct(productId, map, 1);

    }

    private ProductDetailDto parsing(String productId, Map productMap) {
        return ProductDetailDto.builder()
                .productId(productId)
                .name((String) productMap.get("name"))
                .price(Integer.parseInt((String) productMap.get("price")))
                .productType((String) productMap.get("type"))
                .build();

    }


    public StockDto getProductStock(String productId) {

        productRepository.findByProductId(productId)
                .orElseThrow(() -> new BaseException(PRODUCT_NOT_FOUND));

        StockDto productStock = stockServiceClient.getProductStock(productId);

        return productStock;

    }

//    public PostProductRes postNewProduct(PostProductReq postProductReq) {
//
//    }
}
