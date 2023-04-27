package com.dmytrohuk.weborganizer.users;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        new Throwable("User with id " + id + " does not exist")
                )
        );
        return userRepository.findById(id);
    }

//    public User createUser() {
//        return createUser(null);
//    }

    public User createUser(UserCreateDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return userRepository.save(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(
                    new Throwable("User with id " + id + " does not exist")
            )
        );
//        existingUser.setUsername(userDTO.getUsername());
//        existingUser.setPassword(userDTO.getPassword());
//        existingUser.setEmail(userDTO.getEmail());
//        existingUser.setFirstName(userDTO.getFirstName());
//        existingUser.setSurname(userDTO.getSurname());
//        existingUser.setAddress(userDTO.getAddress());
        userMapper.updateUserThroughMapper(userDTO, existingUser);
        return userMapper.toUserDTO(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        new Throwable("User with id " + id + " does not exist")
                )
        );
        userRepository.deleteById(id);
    }
}
