package com.example.productservice.common.data;


import com.example.productservice.client.StockServiceClient;
import com.example.productservice.domain.ProductType;
import com.example.productservice.domain.dto.PostStockReq;
import com.example.productservice.domain.entity.Product;
import com.example.productservice.repository.ProductRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final StockServiceClient stockServiceClient;


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

        Product productFive = Product.builder()
                .productId("140")
                .name("apple")
                .productType(ProductType.GENERAL)
                .price(300)
                .build();

        productRepository.save(productOne);
        productRepository.save(productTwo);
        productRepository.save(productThree);
        productRepository.save(productFour);
        productRepository.save(productFive);

        try {
            stockServiceClient.postStock(changeStock(productOne, 10));
            stockServiceClient.postStock(changeStock(productOne, 10));
            stockServiceClient.postStock(changeStock(productOne, 100));
            stockServiceClient.postStock(changeStock(productOne, 200));
            stockServiceClient.postStock(changeStock(productOne, 300));
        } catch (FeignException e) {
            log.error(e.getMessage());
        }


    }

    private PostStockReq changeStock(Product product, int stock) {
        return PostStockReq.builder()
                .productId(product.getProductId())
                .productType(product.getProductType())
                .stock(stock)
                .build();

    }


}
