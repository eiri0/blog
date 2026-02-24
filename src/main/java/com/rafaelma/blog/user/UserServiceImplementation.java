package com.rafaelma.blog.user;

import com.rafaelma.blog.user.dto.UserResponse;
import com.rafaelma.blog.user.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private ModelMapper mapper;

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
        List<User> users = userRepository.findAll();
        List<UserResponse>  userDTOS = users
                .stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        User user = optionalUser.get();
        user.setUserName(updatedUser.getUserName());
        user.setHashedPassword(updatedUser.getHashedPassword());
        user.setSalt(updatedUser.getSalt());
        return userRepository.save(user);
    }

}

