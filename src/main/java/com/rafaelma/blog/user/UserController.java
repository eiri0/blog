package com.rafaelma.blog.user;

import com.rafaelma.blog.user.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImplementation userService){
        this.userService = userService;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserResponse> getUserByID( @PathVariable("id") Long id) {
        UserResponse userDTO = userService.getUserDTOById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> allUsers = userService.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

}
