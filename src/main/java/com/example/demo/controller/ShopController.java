package com.example.demo.controller;

import com.example.demo.service.ProductService;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ShopController {

    @Autowired
    private ShopService shopStatus;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public String shopStatus(){
        return this.shopStatus.shopOpen();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String productAvailable(@RequestParam String product){
        Boolean productAvailable = productService.ProductAvailable(product);
        if(productAvailable) {
            return(product + " is available");
        }
        else {            return(product + " is not available");
        }
    }


}
