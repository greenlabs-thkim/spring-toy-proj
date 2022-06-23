package com.greenlabs.day.controller;

import com.greenlabs.day.domain.User;
import com.greenlabs.day.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        userService = service;
    }

    @GetMapping("user/regist")
    public String registForm() {
        return "user/registUserForm";
    }

    @PostMapping("user/regist")
    public String regist(User user) {
        userService.regist(user);

        return "redirect:/";
    }

    @GetMapping("user/login")
    public String loginForm() {
        return "user/loginUserForm";
    }

    @PostMapping("user/login")
    public String login(User user) {
        userService.login(user);
        return "redirect:/";
    }
}
