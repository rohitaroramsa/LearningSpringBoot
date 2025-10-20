package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.exception.DuplicateProductException;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import org.springframework.dao.DataIntegrityViolationException;
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
        try {
             productRepository.save(product);
//            System.out.println(rowsUpdated >= 1 ? "product saved" : "product not saved");
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicateProductException(product.getProductName());
        }
        }

    @Transactional
    public int removeProduct(String productName) {
    Integer n = productRepository.deleteByProductNameIgnoreCase(productName);
    if (n==null || n==0){
        throw new ProductNotFoundException("Could not delete, as can not find a product with name: "+ productName);
    }
        return n ;
    }

    public List<ResponseDto> getAllProducts() {
        return productRepository.findAll().stream().map(p-> new ResponseDto(p.getProductName(),p.getPrice(),p.getId())).toList();
    }
}
