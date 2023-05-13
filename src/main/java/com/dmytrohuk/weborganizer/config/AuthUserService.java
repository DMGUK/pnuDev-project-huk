package com.dmytrohuk.weborganizer.config;

import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserAlreadyExistsException;
import com.dmytrohuk.weborganizer.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null){
            throw new UserAlreadyExistsException(
                    new Throwable("User with username %s does not exist".formatted(username))
            );
        }
        return new AuthUser(user);
    }
}
