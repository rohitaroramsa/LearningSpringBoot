package com.example.demo.exception;

public class DuplicateProductException extends RuntimeException{

    public DuplicateProductException(String productName){
super("product name: "+ productName +" already exists!");
    }
}
