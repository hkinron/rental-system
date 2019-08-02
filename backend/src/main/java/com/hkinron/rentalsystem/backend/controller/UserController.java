package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.model.User;
import com.hkinron.rentalsystem.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user")
    public long addNewUser(@RequestBody User user) {
        User userInDb = userService.addNewUser(user);
        log.info("Successfully create user " + user);
        return userInDb.getId();
    }

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        log.info("Successfully get user " + user + " from DB by id " + id);
        return user;
    }

    @GetMapping(path = "/users")
    public Page<User> getAllUsers(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        log.info("Get " + users.getTotalElements() + " users from DB");
        return users;
    }

    @DeleteMapping(path = "/user")
    public long deleteUser(@RequestParam long userId) {
        userService.deleteUser(userId);
        log.info("successfully deleted " + userId + "from DB");
        return userId;
    }


}
