package com.dmytrohuk.weborganizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dmytrohuk.weborganizer.security.JWTGenerator;
import com.dmytrohuk.weborganizer.users.UserCreateDTO;
import com.dmytrohuk.weborganizer.users.UserMapper;
import com.dmytrohuk.weborganizer.users.UserRepository;
import com.dmytrohuk.weborganizer.users.UserService;
import com.dmytrohuk.weborganizer.users.UserViewDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dmytrohuk.weborganizer.users.User;
public class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JWTGenerator jwtGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, userMapper, jwtGenerator);
    }

    @Test
    void testCreateUser() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername("username");
        userCreateDTO.setPassword("password");
        userCreateDTO.setEmail("email.abc@email.com");
        UserViewDTO user = userService.createUser(userCreateDTO);
        String viewDtoUsername = userViewDTO.getUsername();
        String createDtoUsername = userCreateDTO.getUsername();
        assertEquals(viewDtoUsername, createDtoUsername);
    }
}
