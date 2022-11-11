package com.fs12.javaspringboot.admin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.fs12.javaspringboot.admin.Permission.*;

@Configuration
public class AdminConfig {
    @Bean
    CommandLineRunner commandLineRunnerAdmin(AdminRepository adminRepository) {
        return args -> {
            Admin admin1 = new Admin(
                    "General",
                    "Admin",
                    "generaladmin1@petlify.com",
                    "securepassword",
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin2 = new Admin(
                    "General",
                    "Admin",
                    "generaladmin2@petlify.com",
                    "securepassword",
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin3 = new Admin(
                    "General",
                    "Admin",
                    "generaladmin3@petlify.com",
                    "securepassword",
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin4 = new Admin(
                    "General",
                    "Admin",
                    "generaladmin4@petlify.com",
                    "securepassword",
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin5 = new Admin(
                    "Super",
                    "Admin",
                    "superadmin@petlify.com",
                    "securepassword",
                    List.of(ADMINS_READ, ADMINS_WRITE, ORDERS_READ, ORDERS_WRITE, USERS_READ, USERS_WRITE, PRODUCTS_READ, PRODUCTS_WRITE)
            );

            adminRepository.saveAll(
                    List.of(admin1, admin2, admin3, admin4, admin5)
            );
        };
    }
}
