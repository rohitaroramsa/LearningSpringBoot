package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    List<String> products = new ArrayList<>();

    public ProductService(){
        products.addAll(Arrays.asList("TV", "Mobile", "Tablet"));
    }

    public Boolean ProductAvailable(String product){
        Boolean available = products.stream().anyMatch(p->p.equalsIgnoreCase(product));
        return available;
    }

}
