package com.example.productservice.common.data;


import com.example.productservice.domain.ProductType;
import com.example.productservice.domain.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Product productOne = Product.builder()
                .productId("100")
                .name("mac-book")
                .productType(ProductType.RESERVATION)
                .price(50000)
                .build();


        Product productTwo = Product.builder()
                .productId("110")
                .name("iphone")
                .productType(ProductType.RESERVATION)
                .price(9000)
                .build();

        Product productThree = Product.builder()
                .productId("120")
                .name("water")
                .productType(ProductType.GENERAL)
                .price(2500)
                .build();

        Product productFour = Product.builder()
                .productId("130")
                .name("shoes")
                .productType(ProductType.GENERAL)
                .price(1000)
                .build();


        productRepository.save(productOne);
        productRepository.save(productTwo);
        productRepository.save(productThree);
        productRepository.save(productFour);


    }
}