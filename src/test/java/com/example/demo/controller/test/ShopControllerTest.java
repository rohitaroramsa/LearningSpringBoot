package com.example.demo.controller.test;


import com.example.demo.controller.ShopController;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
 import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        ResponseDto response = new ResponseDto("TV", new BigDecimal(1212), 121);

        when(productService.getAllProducts()).thenReturn(List.of(response));

         mockMvc.perform(get("/shop/products/all")).andExpect(status().isOk())
                 .andExpect(jsonPath("$[0].productName").value("TV"))
                 .andExpect(jsonPath("$[0].productCost").value(1212))
                 .andExpect(jsonPath("$[0].id").value(121))
                 .andExpect(jsonPath("$[0].length()").value(3))
                 .andExpect(jsonPath("$.length()").value(1));
    }
}