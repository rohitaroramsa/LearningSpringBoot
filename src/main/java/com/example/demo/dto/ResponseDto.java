package com.example.demo.dto;

import java.math.BigDecimal;

public class ResponseDto {


    private String productName;
    private BigDecimal productCost;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public void setProductCost(BigDecimal productCost) {
        this.productCost = productCost;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ResponseDto() {
    }


    public ResponseDto(String productName, BigDecimal productCost, int id) {
        this.productName = productName;
        this.productCost = productCost;
        this.id = id;
    }



}
