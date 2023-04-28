package com.dmytrohuk.weborganizer.users;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    public UserCreateDTO getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(
                    new Throwable("User with id " + id + " does not exist")
            )
        );
        return userMapper.toUserCreateDTO(existingUser);
    }

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
        userMapper.updateUser(userDTO, existingUser);
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
