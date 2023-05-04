package com.dmytrohuk.weborganizer.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserViewDTO createUser(UserCreateDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null){
            throw new UserAlreadyExistsException(new Throwable("User already exists"));
        }
        return userMapper.toViewDTO(userRepository.save(user));
    }

    @Transactional
    public UserViewDTO updateUser(Long id, UserUpdateDTO updateDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(
                    new Throwable("User with id " + id + " does not exist")
            )
        );
        userMapper.updateUser(updateDTO, existingUser);
        return userMapper.toViewDTO(userRepository.save(existingUser));
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
