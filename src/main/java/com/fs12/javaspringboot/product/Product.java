package com.fs12.javaspringboot.product;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "Name cannot be empty.")
    private String name;
    @NotBlank(message = "Image cannot be empty.")
    private String image;
    @NotBlank(message = "Description cannot be empty.")
    private String description;
    @NotBlank(message = "Pet cannot be empty.")
    private String pet;
    @NotBlank(message = "Subcategory cannot be empty.")
    private String subcategory;
    @NotBlank(message = "Variant cannot be empty.")
    private String variant;
    @NotBlank(message = "Size cannot be empty.")
    private String size;
    @NotNull(message = "Price cannot be empty.")
    private Double price;

    public Product(String name, String image, String description, String pet, String subcategory, String variant, String size, Double price) {
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
