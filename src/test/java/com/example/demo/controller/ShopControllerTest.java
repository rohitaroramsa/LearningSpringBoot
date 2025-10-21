package com.example.demo.controller;


import com.example.demo.dto.ResponseDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
 import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @MockitoBean
    private ShopService shopService;

    @Test
    void getAllProduct() throws Exception {
        Map<String,String> product = new HashMap<>();
        product.put("productName","TV");
        product.put("price","122");
        ResponseDto response = new ResponseDto("TV", new BigDecimal(1212), 121);
        when(productService.getAllProducts()).thenReturn(List.of(response));
         mockMvc.perform(get("/shop/products/all")).andExpect(status().isOk());
    }
}