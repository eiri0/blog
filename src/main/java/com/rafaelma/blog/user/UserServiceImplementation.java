package com.rafaelma.blog.user;

import com.rafaelma.blog.security.PasswordHasher;
import com.rafaelma.blog.user.dto.UserRequest;
import com.rafaelma.blog.user.dto.UserResponse;
import com.rafaelma.blog.user.exception.UserAlreadyExistsException;
import com.rafaelma.blog.user.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
        mapper = new ModelMapper();
    }

    @Override
    public UserResponse getUserDTOById(Long id) {
        return userRepository.findById(id)
                             .map(u -> mapper.map(u, UserResponse.class))
                             .orElseThrow(() -> new UserNotFoundException(id));

    }
    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new UserNotFoundException(id));

    }
    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                             .stream()
                             .map(user -> mapper.map(user, UserResponse.class))
                             .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
       User user =  userRepository.findById(id)
                                  .orElseThrow(() -> new UserNotFoundException(id));
       userRepository.delete(user);
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        if (validateUserName(userRequest.getUserName())) {
            throw new UserAlreadyExistsException(userRequest.getUserName());
        }
        User user = mapper.map(userRequest, User.class);
        String salt = PasswordHasher.generateSalt();
        String hashedPassword = PasswordHasher.hashPassword(userRequest.getPassword(), salt);
        user.setHashedPassword(hashedPassword);
        user.setSalt(salt);
        User savedUser =  userRepository.save(user);
        UserResponse userResponse = mapper.map(savedUser, UserResponse.class);
        return userResponse;
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser =  userRepository.findById(id)
                            .orElseThrow(() -> new UserNotFoundException(id));
        return userRepository.save(existingUser.updatedFrom(updatedUser));
    }

    @Override
    public boolean validateUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }
}

