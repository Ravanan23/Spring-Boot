package com.example.springbootCRUD_h2db.service;

import com.example.springbootCRUD_h2db.entity.*;
import com.example.springbootCRUD_h2db.repository.*;

import java.util.*;

import org.springframework.stereotype.Service;;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // GET ALL User List
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // CREATE User
    public User createUser(User user) {
        return repo.save(user);
    }

    // GET User by ID
    // public User getUserById(Long id) {
    // return repo.findById(id).orElse(null);
    // }

    // GET User by ID
    // public User getUserById(Long id) {
    // return repo.findById(id).orElseThrow(() -> new NoSuchElementException("User
    // not found with id: " + id));
    // }
    public User getUserById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // UPDATE User
    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return repo.save(existingUser);
    }

    // DELETE User
    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        repo.delete(existingUser);
    }


    // Custom query methods-> get by email.
    public User getByEmail(String email, String name) {
        return repo.findByEmail(email, name);
    }

    // Custom query methods-> get by name.
    public List<User> getByName(String name) {
        return repo.findByNameNative(name);
    }

    // Custom query methods-> update name by id.
    public void updateName(Long id, String name) {
        repo.updateUserName(id, name);

        //int a= 10/0; // to test global exception handler for 500 Internal Server Error
    }
}
