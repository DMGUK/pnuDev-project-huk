package com.dmytrohuk.weborganizer.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "{user-id}")
    public Optional<User> getUserById(@PathVariable("user-id") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public void createNewUser(@RequestBody User user){
        userService.createUser(user);
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
    public void updateUser(@PathVariable("user-id") Long userId,@RequestBody UserDTO userDTO){
        userService.updateUser(userId, userDTO);
    }
}
