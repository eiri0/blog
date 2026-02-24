package com.rafaelma.blog.user;

import com.rafaelma.blog.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserDTOById(Long id);
    User getById(Long id);
    List<UserResponse> getAll();
    void deleteUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id, User updatedUser);
}
