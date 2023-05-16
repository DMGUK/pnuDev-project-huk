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
    @Mock
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
        String username = "John";
        User userToCreate = new User();
        userToCreate.setUsername(username);

        UserCreateDTO userCreateDTO = userMapper.toCreateDTO(userToCreate);

        when(userRepository.findByUsername(username)).thenReturn(userToCreate);
        when(userService.createUser(userCreateDTO)).thenReturn(new UserViewDTO());

        UserViewDTO result = userService.createUser(userCreateDTO);

        assertEquals(true, result);
        verify(userRepository, times(1)).findByUsername(username);
        verify(userService, times(1)).createUser(userCreateDTO);
    }
}
