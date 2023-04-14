package com.dmytrohuk.weborganizer.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, UserUpdate userUpdate) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(userUpdate.getUsername());
            existingUser.setPassword(userUpdate.getPassword());
            existingUser.setEmail(userUpdate.getEmail());
            existingUser.setFirstName(userUpdate.getFirstName());
            existingUser.setLastName(userUpdate.getLastName());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalStateException("User with id " + id + " not found");
        }
    }

    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalStateException("User with id " + id + " not found");
        }
    }
}
