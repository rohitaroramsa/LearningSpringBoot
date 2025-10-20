package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    private final ProductService productService;

    // dependency injection
    public ShopController(ShopService shopService, ProductService productService) {
        this.shopService = shopService;
        this.productService = productService;
    }

    @GetMapping(value = "/shop")
    public ResponseEntity<String> shopStatus(){
        boolean shopOpen = Boolean.parseBoolean(this.shopService.shopOpen());
        if(shopOpen){
            return ResponseEntity.ok("shop is open");
        }
        else{
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("shop is not open");
        }
    }

    @GetMapping(value = "/products/{productName}")
    public ResponseEntity<String> productAvailable(@PathVariable String productName){
        boolean productAvailable = productService.isProductAvailable(productName);
        if(productAvailable) {
            return ResponseEntity.status(HttpStatus.OK).body(productName + " is available");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productName + " is not available");
        }
    }

    @PostMapping(value="/products")
    public ResponseEntity<String> addProduct(@Valid @RequestBody RequestDto requestDto){
        Product product = new Product(requestDto.getProductName(), requestDto.getProductCost());
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("product added!");
    }


    @DeleteMapping(value = "/products/{productName}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productName){
        productService.removeProduct(productName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productName + " is deleted!");
    }

    @GetMapping(value="/products/all")
    public ResponseEntity<List<ResponseDto>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
}
