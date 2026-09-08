package com.project.EComApplication.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidation userValidation;
    @Autowired
    UserMapper userMapper;
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public String addUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return "user is already in the system";
        }
        user.setRole(UserRole.CUSTOMER);
        userRepository.save(user);
        return "user added successfully";
    }

    public String removeUserWithId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return "id not exist";
        }
        userRepository.delete(userOptional.get());
        return "removed successfully";
    }

    public Optional<UserResponse> getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> userMapper.toResponse(user));
        //        return userRepository.findById(id);
    }

    public Optional<UserResponse> updateUser(User user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        boolean checkNameInput = null == user.getName() || user.getName().isEmpty();
        boolean checkEmailInput = null == user.getEmail() || user.getEmail().isEmpty();

        User existingUser = userOptional.get();

        if (!checkEmailInput) {
            existingUser.setEmail(user.getEmail());
        }
        if (!checkNameInput) {
            existingUser.setName(user.getName());
        }
        userRepository.save(existingUser);
        return Optional.of(userMapper.toResponse(existingUser));
    }
}