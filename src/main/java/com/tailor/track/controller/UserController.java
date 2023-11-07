package com.tailor.track.controller;

import com.tailor.track.models.User;
import com.tailor.track.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeUser(@RequestParam String userId)
    {
        if(StringUtils.isBlank(userId)) {
            return ResponseEntity.badRequest().body("Invalid userId");
        }
        userService.removeUser(userId);
        return ResponseEntity.ok("User Deleted");
    }

}
