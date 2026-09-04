package com.project.EComApplication.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidation userValidation;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return "user is already in the system";
        }
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

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(User user, Long id) {
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
        return Optional.of(existingUser);
    }
}