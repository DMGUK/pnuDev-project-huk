package com.dmytrohuk.weborganizer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserRepository;

public class UserRepositoryTest {

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindById() {
		User user = new User();
		user.setId(1L);
		user.setUsername("testuser");
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Optional<User> result = userRepository.findById(1L);
		assertEquals(Optional.of(user), result);
	}

	@Test
	public void userRepositoryUsernames(){
		String username = "testuser";
		String password = "testpassword";
		User testUser = new User();
		testUser.setId(4L);
		testUser.setUsername(username);
		testUser.setPassword(password);
		when(userRepository.findByUsername(username)).thenReturn(testUser);
		User resultUser = userRepository.findByUsername(username);
		assertEquals(testUser, resultUser);
	}
}
