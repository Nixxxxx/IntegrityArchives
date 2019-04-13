package com.jiang.kaoyan.controller.user;

import com.jiang.kaoyan.entity.User;
import com.jiang.kaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(String username, String password) {
        System.out.println(username);
        if(userService.login(username, password))
            return userService.findByUsername(username);
        return null;
    }

    @GetMapping("/logout")
    public boolean logout(){
        return true;
    }
}
