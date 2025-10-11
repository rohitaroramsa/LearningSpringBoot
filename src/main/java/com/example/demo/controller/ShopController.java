package com.example.demo.controller;

import com.example.demo.model.Product;
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
    public String productAvailable(@RequestParam String productName){
        Boolean productAvailable = productService.productAvailable(productName);
        if(productAvailable) {
            return(productName + " is available");
        }
        else { return(productName + " is not available");
        }
    }

    @RequestMapping(value="/add-product", method=RequestMethod.POST)
    private void addProduct(@RequestParam String productName, @RequestParam int productCost){
        Product product = new Product(productName, productCost);
        productService.addProduct(product);
    }


    @RequestMapping(value = "/delete-product", method=RequestMethod.DELETE)
    public void deleteProduct(String productName){
        productService.removeProduct(productName);

    }

    @RequestMapping(value="/getAllProduct", method = RequestMethod.GET)
    public void getAllProduct(){
        productService.getAllProduct();
    }
}
