package com.felece.ticketapi.backend.controller;

import com.felece.ticketapi.backend.entity.User;
import com.felece.ticketapi.backend.entity.vm.UpdateUserVM;
import com.felece.ticketapi.backend.entity.vm.UserListVM;
import com.felece.ticketapi.backend.service.UserService;
import com.felece.ticketapi.backend.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public GenericResponse login(@RequestBody User user) {
        userService.saveUser(user);
        return new GenericResponse("User created successfully");
    }

    @GetMapping("/getusers")
    public List<UserListVM> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getuser/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getuser/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/updateuser")
    public GenericResponse updateUser(@RequestBody UpdateUserVM updateUserVM) {
        userService.updateUser(updateUserVM);
        return new GenericResponse("User updated successfully");
    }

    @PostMapping("/deleteuser")
    public GenericResponse deleteUser(@RequestBody String email) {
        System.out.println(email);
        return new GenericResponse(userService.deleteUser(email));
    }
}
