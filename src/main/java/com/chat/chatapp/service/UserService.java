package com.chat.chatapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.chatapp.Model.User;
import com.chat.chatapp.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	   private final UserRepository userRepository;
	  @Autowired
	    public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	  @Transactional
    public User registerUser(User user) {
         return userRepository.save(user);
    }

    // Method to find a user by their email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to delete a user by their ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // Method to validate login (could include password matching logic)
    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;  // In a real app, you would hash the password and compare
        }
        return false;
    }

    // Other business logic methods related to users can go here
}
