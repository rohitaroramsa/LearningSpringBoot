package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;

    public Boolean productAvailable(String product){
        List<Product> products = productRepository.findAll(); // get all products from DB
        return products.stream().anyMatch(p->p.getProductName().equalsIgnoreCase(product));
    }

    public void addProduct(Product product){
        productRepository.save(product);
        System.out.println("product saved");

    }

    @Transactional
    public void removeProduct(String productName) {
    productRepository.deleteByProductNameIgnoreCase(productName);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
