package com.fs12.javaspringboot.util;

public class ProductsNotFoundException extends Exception {
    public ProductsNotFoundException(String message) {
        super(message);
    }
}
