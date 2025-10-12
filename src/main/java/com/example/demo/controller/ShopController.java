package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShopService;
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
    public ResponseEntity<String> addProduct(@RequestParam String productName, @RequestParam int productCost){
        Product product = new Product(productName, productCost);
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("product added!");
    }


    @DeleteMapping(value = "/products/{productName}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productName){
        int n = productService.removeProduct(productName);
        String body = n>0 ? "product deleted":"nothing to delete";
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping(value="/products/all")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }
}
