package org.ecommercems.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = new User(user.getUsername(), user.getPassword());
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{username}").
                buildAndExpand(newUser.getUsername())
                .toUri();
        return ResponseEntity.created(location).body(newUser);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        System.out.println(username + "Loggedddddd Hereeeeeee");
        User user = new User(username, username);
        return user;
    }
}