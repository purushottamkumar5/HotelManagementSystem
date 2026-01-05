package com.Icwd.user.service.controllers;

import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable String id)
    {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
