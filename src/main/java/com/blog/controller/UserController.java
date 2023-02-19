package com.blog.controller;

import com.blog.dto.UserDto;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("public/api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping("/main")
    public String getMainWindow() {
        return "user/main-window";
    }

    @GetMapping("/new")
    public String getCreateUserWindow(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/create-user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") UserDto dto) {
        service.save(dto);
        return "user/get-all-users";
    }

    @GetMapping("/{userId}/edit")
    public String getUpdateUserWindow() {
        return "";
    }

    @PatchMapping("/{userId}")
    public String updateUser() {
        return "";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser() {
        return "";
    }

    @GetMapping()
    public String getAllUsers() {
        return "user/get-all-users";
    }

    @GetMapping("/{userId}")
    public String getUserByUserId() {
        return "";
    }
}