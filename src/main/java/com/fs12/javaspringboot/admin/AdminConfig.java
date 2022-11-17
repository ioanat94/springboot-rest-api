package com.fs12.javaspringboot.admin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.fs12.javaspringboot.admin.Permission.*;

@Configuration
public class AdminConfig {
    @Bean
    CommandLineRunner commandLineRunnerAdmin(AdminRepository adminRepository) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        return args -> {
            Admin admin1 = new Admin(
                    "General 1",
                    "Admin",
                    "generaladmin1@petlify.com",
                    passwordEncoder.encode("Securepassword1@"),
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin2 = new Admin(
                    "General 3",
                    "Admin",
                    "generaladmin2@petlify.com",
                    passwordEncoder.encode("Securepassword1@"),
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin3 = new Admin(
                    "General 2",
                    "Admin",
                    "generaladmin3@petlify.com",
                    passwordEncoder.encode("Securepassword1@"),
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin4 = new Admin(
                    "General 4",
                    "Admin",
                    "generaladmin4@petlify.com",
                    passwordEncoder.encode("Securepassword1@"),
                    List.of(ADMINS_READ, ORDERS_READ, USERS_READ, PRODUCTS_READ)
            );

            Admin admin5 = new Admin(
                    "Super",
                    "Admin",
                    "superadmin@petlify.com",
                    passwordEncoder.encode("Securepassword1@"),
                    List.of(ADMINS_READ, ADMINS_WRITE, ORDERS_READ, ORDERS_WRITE, USERS_READ, USERS_WRITE, PRODUCTS_READ, PRODUCTS_WRITE)
            );

            adminRepository.saveAll(
                    List.of(admin1, admin2, admin3, admin4, admin5)
            );
        };
    }
}
