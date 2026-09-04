package com.project.EComApplication.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String removeUserWithId(@PathVariable Long id) {
        return userService.removeUserWithId(id);
    }

    @PutMapping("/updateUser/{id}")
    public Optional<User> updateUser(@RequestBody User user, @PathVariable Long id){
        return userService.updateUser(user,id);
    }
}
