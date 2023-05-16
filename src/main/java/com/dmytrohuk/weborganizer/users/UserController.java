package com.dmytrohuk.weborganizer.users;

import com.dmytrohuk.weborganizer.security.AuthResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "{id}")
    public UserViewDTO getUserById(@PathVariable("id") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public void createNewUser(@RequestBody UserCreateDTO userDTO){
        userService.createUser(userDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO userLogin(@RequestBody UserLoginDTO userDTO){
       return userService.userLogin(userDTO);
    }

    @PostMapping("/moderator")
    public void createModerator(@RequestBody UserCreateDTO userDTO){
        userService.createModerator(userDTO);
    }


    @DeleteMapping(path="{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") Long userId, @RequestBody UserUpdateDTO updateDTO){
        userService.updateUser(userId, updateDTO);
    }

    @PutMapping(path = "/moderator/{id}")
    public void updateToModerator(@PathVariable("id") Long userId){
        userService.updateToModerator(userId);
    }
}
