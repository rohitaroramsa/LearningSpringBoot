package com.example.demo.exceptions;

public class DuplicateProductException extends RuntimeException{

    public DuplicateProductException(String productName){
super("product name: "+ productName +" already exists!");
    }
}
