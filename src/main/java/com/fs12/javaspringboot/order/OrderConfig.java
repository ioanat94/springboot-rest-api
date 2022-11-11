package com.fs12.javaspringboot.order;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class OrderConfig {
    @Bean
    CommandLineRunner commandLineRunnerOrder(OrderRepository orderRepository) {
        return args -> {
            Order order1 = new Order(
                    new Date(),
                    "processing",
                    "ioanatiplea@gmail.com",
                    List.of("1", "2"),
                    15.98,
                    "Test str. no. 10"
            );

            Order order2 = new Order(
                    new Date(),
                    "processing",
                    "ioanatiplea@gmail.com",
                    List.of("1", "2"),
                    15.98,
                    "Test str. no. 10"
            );

            Order order3 = new Order(
                    new Date(),
                    "processing",
                    "ioanatiplea@gmail.com",
                    List.of("1", "2"),
                    15.98,
                    "Test str. no. 10"
            );

            Order order4 = new Order(
                    new Date(),
                    "processing",
                    "ioanatiplea@gmail.com",
                    List.of("1", "2"),
                    15.98,
                    "Test str. no. 10"
            );

            Order order5 = new Order(
                    new Date(),
                    "processing",
                    "ioanatiplea@gmail.com",
                    List.of("1", "2"),
                    15.98,
                    "Test str. no. 10"
            );

            orderRepository.saveAll(
                    List.of(order1, order2, order3, order4, order5)
            );
        };
    }
}
