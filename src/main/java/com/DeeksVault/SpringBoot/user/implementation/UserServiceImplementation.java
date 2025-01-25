package com.DeeksVault.SpringBoot.user.implementation;

import com.DeeksVault.SpringBoot.user.User;
import com.DeeksVault.SpringBoot.user.UserRepository;
import com.DeeksVault.SpringBoot.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUser(id); // Fetch the existing user
        existingUser.setName(updatedUser.getName());
        existingUser.setCurrentCompany(updatedUser.getCurrentCompany());
        existingUser.setCurrentPackage(updatedUser.getCurrentPackage());
        existingUser.setSkills(updatedUser.getSkills());
        existingUser.setDescription(updatedUser.getDescription());
        return userRepository.save(existingUser);
    }
}

