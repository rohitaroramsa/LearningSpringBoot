package com.example.demo.controller.test.integration;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    ShopService shopService;

    @Autowired
    ProductService  productService;

    @Test
    void addProduct(){
        Product requested = new Product("TV", BigDecimal.valueOf(12));
        productService.addProduct(requested);
        List<ResponseDto> products = productService.getAllProducts();
        assertTrue(products.stream().anyMatch(e -> e.getProductName().equals("TV")));
    }
}
