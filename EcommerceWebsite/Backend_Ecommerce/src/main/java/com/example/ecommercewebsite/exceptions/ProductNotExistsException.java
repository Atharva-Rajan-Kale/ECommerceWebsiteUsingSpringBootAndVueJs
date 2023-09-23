package com.example.ecommercewebsite.exceptions;

public class ProductNotExistsException extends IllegalArgumentException{
    public ProductNotExistsException(String msg){
        super(msg);
    }
}
