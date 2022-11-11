package com.fs12.javaspringboot.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private String image;
    private String description;
    private Pet pet;
    private String subcategory;
    private ArrayList<String> variants;
    private ArrayList<String> sizes;
    private double price;
}