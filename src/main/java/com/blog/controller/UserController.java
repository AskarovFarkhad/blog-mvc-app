package com.blog.controller;

import com.blog.dto.UserDto;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("public/api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping()
    public String getMainWindow() {
        return "main-blog";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/create-user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") UserDto dto) {
        service.save(dto);
        return "redirect:/public/api/v1/users";
    }

    @GetMapping("/{userId}/edit")
    public String update(@PathVariable("userId") UUID userId) {
        // TODO реализовать
        return "";
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") UUID userId) {
        // TODO реализовать
        return "";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") UUID userId) {
        // TODO реализовать
        return "";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<UserDto> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "user/get-all-users";
    }
}