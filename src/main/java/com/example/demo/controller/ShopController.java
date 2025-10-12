package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ShopController {

    @Autowired
    private ShopService shopStatus;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/shop")
    public ResponseEntity<String> shopStatus(){
        boolean shopOpen = Boolean.parseBoolean(this.shopStatus.shopOpen());
        if(shopOpen){
            return ResponseEntity.ok("shop is open");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("shop is not open");
        }
    }

    @GetMapping(value = "/products")
    public ResponseEntity<String> productAvailable(@RequestParam String productName){
        Boolean productAvailable = productService.productAvailable(productName);
        if(productAvailable) {
            return ResponseEntity.status(HttpStatus.OK).body(productName + " is available");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(productName + " is not available");
        }
    }

    @PostMapping(value="/add-product")
    public ResponseEntity<String> addProduct(@RequestParam String productName, @RequestParam int productCost){
        Product product = new Product(productName, productCost);
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body("product added!");
    }


    @DeleteMapping(value = "/delete-product")
    public ResponseEntity<String> deleteProduct(@RequestParam String productName){
        int n = productService.removeProduct(productName);
        String body = n>0 ? "product deleted":"nothing to delete";
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping(value="/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
    }
}
