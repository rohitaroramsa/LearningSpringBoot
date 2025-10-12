package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    // constructor dependency injection
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean isProductAvailable(String product){
        List<Product> products = productRepository.findAll(); // get all products from DB
        return products.stream().anyMatch(p->p.getProductName().equalsIgnoreCase(product));
    }

    public void addProduct(Product product){
        productRepository.save(product);
        System.out.println("product saved");

    }

    @Transactional
    public int removeProduct(String productName) {
    Integer n = productRepository.deleteByProductNameIgnoreCase(productName);
        return n!=null?n:0;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
