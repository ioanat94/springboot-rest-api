package com.fs12.javaspringboot.product;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private int id;
    private String name;
    private String image;
    private String description;
    private String pet;
    private String subcategory;
    private String variant;
    private String size;
    private double price;

    public Product(String name, String image, String description, String pet, String subcategory, String variant, String size, double price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.pet = pet;
        this.subcategory = subcategory;
        this.variant = variant;
        this.size = size;
        this.price = price;
    }
}
