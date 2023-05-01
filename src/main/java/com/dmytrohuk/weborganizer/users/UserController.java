package com.dmytrohuk.weborganizer.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{user-id}")
    public UserViewDTO getUserById(@PathVariable("user-id") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public void createNewUser(@RequestBody UserCreateDTO userDTO){
        userService.createUser(userDTO);
    }

    @DeleteMapping(path="{user-id}")
    public void deleteUser(@PathVariable("user-id") Long userId){
        userService.deleteUser(userId);
    }


    // create a RequestBody to the User
        /*
    @RequestBody UpdateUser updateUser
    *
    */
    @PutMapping(path = "{user-id}")
    public void updateUser(@PathVariable("user-id") Long userId, @RequestBody UserUpdateDTO updateDTO){
        userService.updateUser(userId, updateDTO);
    }
}
