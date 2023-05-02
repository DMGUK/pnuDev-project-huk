package com.dmytrohuk.weborganizer.users;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserViewDTO getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(
                    new Throwable("User with id " + id + " does not exist")
            )
        );
        return userMapper.toViewDTO(existingUser);
    }

    public User createUser(UserCreateDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        return userRepository.save(user);
    }

    @Transactional
    public UserUpdateDTO updateUser(Long id, UserUpdateDTO updateDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(
                    new Throwable("User with id " + id + " does not exist")
            )
        );
        userMapper.updateUser(updateDTO, existingUser);
        return userMapper.toUpdateDTO(userRepository.save(existingUser));
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
