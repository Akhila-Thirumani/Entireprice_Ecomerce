
package com.ecommerce.service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    public boolean addUser(User user) {

        if (user == null) {
            return false;
        }

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return false;
        }

        return userRepository.saveUser(user);
    }

    public boolean deleteUser(int userId) {
        return userRepository.delete(userId);
    }
}
