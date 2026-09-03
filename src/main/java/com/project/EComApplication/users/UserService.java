package com.project.EComApplication.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    List<User> userList = new ArrayList<>();
    Long nextId = 1L;

    @Autowired
    UserValidation userValidation;

    public List<User> getAllUsers() {
        return userList;
    }

    public String addUser(User user) {
        boolean checkEmailExists = userValidation.checkEmailExists(userList, user);
        if (checkEmailExists) {
            return "Email already exists";
        }
        user.setId(nextId++);
        userList.add(user);
        return "User added Successfully";
    }

    public String removeUserWithId(Long id) {
        boolean checkIdIsExisting = userValidation.checkIdIsExisting(userList, id);
        if (!checkIdIsExisting) {
            return "id not exist";
        }
        userList.removeIf(i -> i.getId().equals(id));
        return "removed successfully";
    }

    public User getUser(Long id) {
        return userList.stream().filter(i -> (i.getId()).equals(id)).findFirst().orElseThrow(() ->
                new RuntimeException("invalid id"));
    }

    public Optional<User> updateUser(User user, Long id) {
        Optional<User> userOptional = userList.stream().filter(i -> i.getId().equals(id)).findFirst();
        if(userOptional.isEmpty()){
            return Optional.empty();
        }
        boolean checkNameInput = null == user.getName() || user.getName().isEmpty();
        boolean checkEmailInput =  null == user.getEmail() || user.getEmail().isEmpty();

        User existingUser = userOptional.get();

        if(!checkEmailInput){
            existingUser.setEmail(user.getEmail());
        }
        if(!checkNameInput){
            existingUser.setName(user.getName());
        }

        return Optional.of(existingUser);
    }
}