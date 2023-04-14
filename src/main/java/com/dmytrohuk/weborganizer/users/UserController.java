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

    @GetMapping
    public List<User> getStudents(){
        return userService.getAllUsers();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody User user){
        userService.createUser(user);
    }

    @DeleteMapping(path="{userId}")
    public void deleteStudent(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }


    // create a RequestBody to the User
        /*
    @RequestBody UpdateUser updateUser
    *
    */
    @PutMapping(path = "{userId}")
    public void updateStudent(@PathVariable("userId") Long userId,@RequestBody UserUpdate userUpdate){
        userService.updateUser(userId, userUpdate);
    }
}
