package com.example.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="product_name", nullable = false, unique = true)
    private String productName;
    @Column(name="product_price", nullable = false)
    private BigDecimal price;

    public Product() { }

    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return String.format("products{ product-name=%s, price=%f } ",this.getProductName(), this.getPrice());
    }
}
