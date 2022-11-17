package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.product.Product;
import com.fs12.javaspringboot.product.ProductRepository;
import com.fs12.javaspringboot.user.User;
import com.fs12.javaspringboot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class OrderConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Bean
    CommandLineRunner commandLineRunnerOrder(OrderRepository orderRepository) {
        User user1 = new User(
                "Ioana",
                "Tiplea",
                "ioanatiplea94@gmail.com",
                "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                false
        );

        User user2 = new User(
                "John",
                "Doe",
                "johndoe@gmail.com",
                "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                false
        );

        userRepository.save(user1);
        userRepository.save(user2);

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

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        Product[] productArray1 = new Product[] {product1, product2};
        Product[] productArray2 = new Product[] {product3, product4};

        return args -> {
            Order order1 = new Order(
                    new Date(),
                    "confirmed",
                    user1,
                    new ArrayList<>(Arrays.asList(productArray1)),
                    25.98,
                    "Test str. no. 10"
            );

            Order order2 = new Order(
                    new Date(),
                    "processing",
                    user2,
                    new ArrayList<>(Arrays.asList(productArray2)),
                    15.98,
                    "Test str. no. 10"
            );

            orderRepository.saveAll(
                    List.of(order1, order2)
            );
        };
    }
}
