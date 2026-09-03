package com.project.EComApplication.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(userList.stream().filter(i -> i.getId().equals(id)).findFirst());
        return userList.stream().filter(i -> i.getId() == (id)).findFirst().orElseThrow(() ->
                new RuntimeException("invalid id"));
    }
}