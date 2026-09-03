package com.project.EComApplication.users;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidation {

    public boolean checkIdIsExisting(List<User> userList, Long id) {
        return userList.stream().anyMatch(i -> i.getId().equals(id));
    }

    public boolean checkEmailExists(List<User> userList, User user) {
        return userList.stream().anyMatch(i -> i.getEmail().equals(user.getEmail()));
    }
}
