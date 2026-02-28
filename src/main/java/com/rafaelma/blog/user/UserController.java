package com.rafaelma.blog.user;

import com.rafaelma.blog.shared.ApiResponse;
import com.rafaelma.blog.user.dto.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rafaelma.blog.shared.ApiResponseHandler.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUserByID(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        UserResponse userDTO = userService.getUserDTOById(id);
        return new ResponseEntity<>(success(userDTO, "User retrieved successfully", httpServletRequest.getRequestURI()), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers(HttpServletRequest httpServletRequest) {
        List<UserResponse> allUsers = userService.getAll();
        return new ResponseEntity<>(success(allUsers, "Users retrieved successfully", httpServletRequest.getRequestURI()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, Long id) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}
