package com.example.productservice.repository;


import com.example.productservice.domain.ProductType;
import com.example.productservice.domain.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findALlByProductType(ProductType type);

    Optional<Product> findByProductId(String productId);







}
