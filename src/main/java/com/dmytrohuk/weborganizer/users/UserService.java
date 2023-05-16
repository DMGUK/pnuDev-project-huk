package com.dmytrohuk.weborganizer.users;

import com.dmytrohuk.weborganizer.security.AuthResponseDTO;
import com.dmytrohuk.weborganizer.security.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

//    private final AuthenticationManager authenticationManager;

    private final JWTGenerator jwtGenerator;

    public UserViewDTO getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException("User with id %d does not exist".formatted(id))
        );
        return userMapper.toViewDTO(existingUser);
    }

    public AuthResponseDTO userLogin(UserLoginDTO userDTO){
        String token = jwtGenerator.generateToken(userDTO.getUsername());
        return new AuthResponseDTO(token);
    }

    public UserViewDTO createUser(UserCreateDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null){
            throw new UserAlreadyExistsException("User already exists".formatted());
        }
        user.setRole(UserRole.USER);
        return userMapper.toViewDTO(userRepository.save(user));
    }

    public UserViewDTO createModerator(UserCreateDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null){
            throw new UserAlreadyExistsException("User already exists".formatted());
        }
        user.setRole(UserRole.MODERATOR);
        return userMapper.toViewDTO(userRepository.save(user));
    }

    @Transactional
    public UserViewDTO updateUser(Long id, UserUpdateDTO updateDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id %d does not exist".formatted(id))
        );
        userMapper.updateUser(updateDTO, existingUser);
        return userMapper.toViewDTO(userRepository.save(existingUser));
    }

    public UserViewDTO updateToModerator(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id %d does not exist".formatted(id))
        );
        existingUser.setRole(UserRole.MODERATOR);
        return userMapper.toViewDTO(userRepository.save(existingUser));
    }

    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id %d does not exist".formatted(id))
        );
        userRepository.deleteById(id);
    }
}
