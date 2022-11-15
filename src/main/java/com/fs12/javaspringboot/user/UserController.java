package com.fs12.javaspringboot.user;

import com.fs12.javaspringboot.util.EmailAlreadyInUse;
import com.fs12.javaspringboot.util.UserNotFoundException;
import com.fs12.javaspringboot.util.UsersNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() throws UsersNotFoundException {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path = "{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable int userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) throws EmailAlreadyInUse {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }
}
