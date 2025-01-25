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

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUser(id); // Fetch the existing user
        existingUser.setName(updatedUser.getName());
        existingUser.setCurrentCompany(updatedUser.getCurrentCompany());
        existingUser.setCurrentPackage(updatedUser.getCurrentPackage());
        existingUser.setSkills(updatedUser.getSkills());
        existingUser.setDescription(updatedUser.getDescription());
        return userRepository.save(existingUser);
    }

    @Override
    public void updateResumePath(Long userId, String resumePath) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setResumePath(resumePath);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
}

