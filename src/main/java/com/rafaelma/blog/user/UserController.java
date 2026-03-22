package com.rafaelma.blog.user;

import com.rafaelma.blog.shared.ApiResponse;
import com.rafaelma.blog.user.dto.UserResponse;
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
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") Long id) {
        return success(userService.getUserDTOById(id), "User retrieved successfully");
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return success(userService.getAll(), "Users retrieved successfully");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {       
        userService.deleteUserById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> saveUser(@RequestBody User user) {
        return success(userService.saveUser(user), "User created successfully");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<User> updateUser(@RequestBody User user, Long id) {
        return success(userService.updateUser(id, user), "User updated successfully");
    }

}
