package com.DeeksVault.SpringBoot.user;

import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();
    User getUser(Long id);
    User updateUser(Long id, User updatedUser);
}
