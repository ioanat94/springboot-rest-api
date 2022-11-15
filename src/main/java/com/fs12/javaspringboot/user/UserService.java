package com.fs12.javaspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(int userId) {
        return userRepository.findById(userId);
    }

    public User addUser(User user) {
        user.setIsBanned(false);
        return userRepository.save(user);
    }

    public String deleteUser(int userId) {
        boolean exists = userRepository.existsById(userId);

        if(!exists) {
            throw new IllegalStateException("User with id " + userId + " does not exist.");
        }

        userRepository.deleteById(userId);

        return "User with id " + userId + " deleted successfully.";
    }

    @Transactional
    public User updateUser(int userId, User user) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exist."));

        if(user.getFirstName() != null && user.getFirstName().length() > 0 && !Objects.equals(foundUser.getFirstName(), user.getFirstName())) {
            foundUser.setFirstName(user.getFirstName());
        }

        if(user.getLastName() != null && user.getLastName().length() > 0 && !Objects.equals(foundUser.getLastName(), user.getLastName())) {
            foundUser.setLastName(user.getLastName());
        }

        if(user.getImage() != null && user.getImage().length() > 0 && !Objects.equals(foundUser.getImage(), user.getImage())) {
            foundUser.setImage(user.getImage());
        }

        if(user.getIsBanned() != null && !Objects.equals(foundUser.getIsBanned(), user.getIsBanned())) {
            foundUser.setIsBanned(user.getIsBanned());
        }

        return foundUser;
    }
}
