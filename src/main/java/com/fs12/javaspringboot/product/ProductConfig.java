package com.fs12.javaspringboot.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunnerProduct(ProductRepository productRepository) {
        return args -> {
            Product product1 = new Product(
                    "Cat Toy",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Fun cat toy",
                    "cats",
                    "toys",
                    "mouse",
                    "small",
                    9.99
            );

            Product product2 = new Product(
                    "Cat Food",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Delicious cat food",
                    "cats",
                    "food",
                    "chicken",
                    "medium",
                    5.99
            );

            Product product3 = new Product(
                    "Cat Bed",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Soft cat bed",
                    "cats",
                    "beds",
                    "blue",
                    "small",
                    19.99
            );

            Product product4 = new Product(
                    "Cat Brush",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Nice cat brush",
                    "cats",
                    "hygiene",
                    "bristles",
                    "small",
                    3.99
            );

            Product product5 = new Product(
                    "Cat Travel Bag",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Comfy cat travel bag",
                    "cats",
                    "other",
                    "plastic",
                    "medium",
                    29.99
            );

            Product product6 = new Product(
                    "Dog Toy",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Fun dog toy",
                    "dogs",
                    "toys",
                    "mouse",
                    "small",
                    9.99
            );

            Product product7 = new Product(
                    "Dog Food",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Delicious dog food",
                    "dogs",
                    "food",
                    "chicken",
                    "medium",
                    5.99
            );

            Product product8 = new Product(
                    "Dog Bed",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Soft dog bed",
                    "dogs",
                    "beds",
                    "blue",
                    "small",
                    19.99
            );

            Product product9 = new Product(
                    "Dog Brush",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Nice dog brush",
                    "dogs",
                    "hygiene",
                    "bristles",
                    "small",
                    3.99
            );

            Product product10 = new Product(
                    "Dog Travel Bag",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    "Comfy dog travel bag",
                    "dogs",
                    "other",
                    "plastic",
                    "medium",
                    29.99
            );

            productRepository.saveAll(
                    List.of(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10)
            );
        };
    }
}