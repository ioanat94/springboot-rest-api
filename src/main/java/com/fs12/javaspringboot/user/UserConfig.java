package com.fs12.javaspringboot.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository){
        return args -> {
            User user1 = new User(
                    "Ioana",
                    "Tiplea",
                    "ioanatiplea94@gmail.com",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    false
            );

            User user2 = new User(
                    "Ioana",
                    "Tiplea",
                    "ioanatiplea95@gmail.com",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    false
            );

            User user3 = new User(
                    "Ioana",
                    "Tiplea",
                    "ioanatiplea96@gmail.com",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    false
            );

            User user4 = new User(
                    "Ioana",
                    "Tiplea",
                    "ioanatiplea97@gmail.com",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    false
            );

            User user5 = new User(
                    "Ioana",
                    "Tiplea",
                    "ioanatiplea98@gmail.com",
                    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__480.png",
                    false
            );

            userRepository.saveAll(
                    List.of(user1, user2, user3, user4, user5)
            );
        };
    }
}
