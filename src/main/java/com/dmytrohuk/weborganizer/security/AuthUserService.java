package com.dmytrohuk.weborganizer.security;

import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserMapper;
import com.dmytrohuk.weborganizer.users.UserNotFoundException;
import com.dmytrohuk.weborganizer.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public AuthUserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UserNotFoundException("User with username %s does not exist".formatted(username));
        }
        return userMapper.toAuthUser(user);
    }
}
