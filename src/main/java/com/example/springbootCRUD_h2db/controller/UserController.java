package com.example.springbootCRUD_h2db.controller;

import com.example.springbootCRUD_h2db.service.UserService;

import jakarta.validation.Valid;

import com.example.springbootCRUD_h2db.entity.User;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    // READ ALL
    @GetMapping("/get_all")
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

  
    // READ BY ID
    @GetMapping("/get_by_id/{id}")
    public User getUser(@Valid @PathVariable Long id) {
        return service.getUserById(id);
    }
    // //Read by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<User> getUserById(@PathVariable Long id) {
    // return service.getUserById(id)
    // .map(ResponseEntity::ok)
    // .orElse(ResponseEntity.notFound().build());
    // }

    @PostMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = service.updateUser(id, user); // Save to DB
        return ResponseEntity.ok(updatedUser); // Return updated user
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        Map<String, String> response = new HashMap<>();
        response.put("message", "User deleted successfully");
        return ResponseEntity.ok(response); // HTTP 200
    }

    // READ BY EMAIL (JPQL)
    @GetMapping("/customquery/email/name")
    public User getByEmail(@RequestParam String email ,@RequestParam String name) {
        return service.getByEmail(email, name);
    }

    // READ BY NAME (Native)
    @GetMapping("/customquery/name/{name}")
    public List<User> getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    // UPDATE
    @PutMapping("/customquery/{id}/name")
    public ResponseEntity<String> updateName(@PathVariable Long id,
            @RequestParam String name) {
        service.updateName(id, name);
        return ResponseEntity.ok("User updated successfully");
    }
}
