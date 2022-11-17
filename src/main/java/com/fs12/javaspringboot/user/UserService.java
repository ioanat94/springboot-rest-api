package com.fs12.javaspringboot.user;

import com.fs12.javaspringboot.util.EmailAlreadyInUse;
import com.fs12.javaspringboot.util.UserNotFoundException;
import com.fs12.javaspringboot.util.UsersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<User> getUsers() throws UsersNotFoundException {
        List<User> users = userRepository.findAll();

        if(!users.isEmpty()) {
            return users;
        } else {
            throw new UsersNotFoundException("No users found.");
        }
    }

    public List<User> getUsersWithSorting(String field) throws UsersNotFoundException {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, field));

        if(!users.isEmpty()) {
            return users;
        } else {
            throw new UsersNotFoundException("No users found.");
        }
    }

    public Optional<User> getUser(int userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return user;
        } else {
            throw new UserNotFoundException("User with id " + userId + " does not exist.");
        }
    }

    public User addUser(User user) throws EmailAlreadyInUse {
        User foundUser = userRepository.getUserByEmail(user.getEmail());

        if(foundUser != null) {
            throw new EmailAlreadyInUse("An account already exists for this email address.");
        }

        user.setIsBanned(false);
        return userRepository.save(user);
    }

    public String deleteUser(int userId) throws UserNotFoundException {
        boolean exists = userRepository.existsById(userId);

        if(!exists) {
            throw new UserNotFoundException("User with id " + userId + " does not exist.");
        }

        userRepository.deleteById(userId);

        return "User with id " + userId + " deleted successfully.";
    }

    @Transactional
    public User updateUser(int userId, User user) throws UserNotFoundException {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id " + userId + " does not exist."));

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
