package com.fs12.javaspringboot.order;

import com.fs12.javaspringboot.user.User;
import com.fs12.javaspringboot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class OrderConfig {
    @Autowired
    private UserRepository userRepository;

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

        return args -> {
            Order order1 = new Order(
                    new Date(),
                    "processing",
                    user1,
                    List.of(1, 2),
                    15.98,
                    "Test str. no. 10"
            );

            Order order2 = new Order(
                    new Date(),
                    "processing",
                    user2,
                    List.of(1, 2),
                    15.98,
                    "Test str. no. 10"
            );

            Order order3 = new Order(
                    new Date(),
                    "processing",
                    user1,
                    List.of(1, 2),
                    15.98,
                    "Test str. no. 10"
            );

            Order order4 = new Order(
                    new Date(),
                    "processing",
                    user2,
                    List.of(1, 2),
                    15.98,
                    "Test str. no. 10"
            );

            Order order5 = new Order(
                    new Date(),
                    "processing",
                    user1,
                    List.of(1, 2),
                    15.98,
                    "Test str. no. 10"
            );

            orderRepository.saveAll(
                    List.of(order1, order2, order3, order4, order5)
            );
        };
    }
}
