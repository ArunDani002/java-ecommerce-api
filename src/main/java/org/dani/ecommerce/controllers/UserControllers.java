package org.dani.ecommerce.controllers;

import org.dani.ecommerce.models.UserModel;
import org.dani.ecommerce.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")   // allows React frontend access
public class UserControllers {

    private final UserService userService;

    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    //TO create new user
    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    //Getting all the users
    @GetMapping
    public List<UserModel> getAllUser() {
        return userService.getAllUsers();
    }

    //Getting single user
    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // updating particular user
    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel userModel){
        return userService.updateUser(id, userModel);
    }

}
