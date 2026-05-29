package com.rafaelma.blog.user;

import static com.rafaelma.blog.security.PasswordHasher.hashPassword;
import com.rafaelma.blog.user.dto.UserRequest;
import com.rafaelma.blog.user.dto.UserResponse;
import com.rafaelma.blog.user.exception.UserAlreadyExistsException;
import com.rafaelma.blog.user.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
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
    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        if (isUserTaken(userRequest.getUserName())) {
            throw new UserAlreadyExistsException(userRequest.getUserName());
        }
        
        String hashedPassword = hashPassword(userRequest.getPassword());
        User user = mapper.map(userRequest, User.class);
        user.setHashedPassword(hashedPassword);
        User savedUser =  userRepository.save(user);
        return mapper.map(savedUser, UserResponse.class);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User user =  userRepository.findById(id)
                                   .orElseThrow(() -> new UserNotFoundException(id));
        return userRepository.save(user.updatedFrom(updatedUser));
    }

    @Override
    public boolean isUserTaken(String userName) {
        return userRepository.existsByUserNameIgnoreCase(userName);
    }
}

