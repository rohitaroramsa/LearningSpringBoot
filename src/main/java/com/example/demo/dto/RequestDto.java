package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class RequestDto {

    @NotBlank(message = "Product name is required")
    private String productName;
    private BigDecimal productCost;

    public RequestDto(BigDecimal productCost, String productName) {
        this.productCost = productCost;
        this.productName = productName;
    }

    public RequestDto() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public void setProductCost(BigDecimal productCost) {
        this.productCost = productCost;
    }
}
