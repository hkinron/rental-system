package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.domain.User;
import com.hkinron.rentalsystem.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user")
    public long addNewUser(@RequestBody User user) {
        User userInDb = userService.addNewUser(user);
        logger.info("Successfully create user " + user);
        return userInDb.getId();
    }

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        logger.info("Successfully get user " + user + " from DB by id " + id);
        return user;
    }

    @GetMapping(path = "/users")
    public Page<User> getAllUsers(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<User> users = userService.getAllUsers(pageable);
        logger.info("Get " + users.getTotalElements() + " users from DB");
        return users;
    }

    @DeleteMapping(path = "/user")
    public long deleteUser(@RequestParam long userId) {
        userService.deleteUser(userId);
        logger.info("successfully deleted " + userId + "from DB");
        return userId;
    }


}
