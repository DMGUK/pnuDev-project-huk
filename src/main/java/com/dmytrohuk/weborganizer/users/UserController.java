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

    @GetMapping(path = "{userId}")
    public Optional<User> getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public void createNewUser(@RequestBody User user){
        userService.createUser(user);
    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }


    // create a RequestBody to the User
        /*
    @RequestBody UpdateUser updateUser
    *
    */
    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,@RequestBody UserUpdate userUpdate){
        userService.updateUser(userId, userUpdate);
    }
}
