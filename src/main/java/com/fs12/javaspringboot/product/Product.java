package com.fs12.javaspringboot.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private String image;
    private String description;
    private Pet pet;
    private String subcategory;
    private String[] variants;
    private String[] sizes;
    private double price;
}
