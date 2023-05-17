package com.dmytrohuk.weborganizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dmytrohuk.weborganizer.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@AutoConfigureTestDatabase
public class UserServiceTest {
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private JWTGenerator jwtGenerator;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userMapper, jwtGenerator);
    }

    @Test
    void testCreateUser() {
        String username = "John";
        User userToCreate = new User();
        userToCreate.setUsername(username);

        // Act
        UserViewDTO savedUser = userService.createUser(userMapper.toCreateDTO(userToCreate));

        // Assert
        assertEquals(username, savedUser.getUsername());
    }
}
