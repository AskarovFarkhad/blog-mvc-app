package com.blog.controller;

import com.blog.dto.UUID;
import com.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("public/api/v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new UUID());
        return "user/create-user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") UUID dto) {
        service.save(dto);
        return "redirect:/public/api/v1/posts";
    }

    @GetMapping("/{userId}/edit")
    public String updateUser(@PathVariable("userId") java.util.UUID userId, Model model) throws SQLException {
        model.addAttribute("user", service.getById(userId));
        return "user/update-user";
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") java.util.UUID userId,
                             @ModelAttribute("user") UUID dto) throws SQLException {
        service.update(userId, dto);
        return "redirect:/public/api/v1/users/all";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") java.util.UUID userId) {
        service.delete(userId);
        return "redirect:/public/api/v1/users/all";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) throws SQLException {
        List<UUID> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "user/get-all-users";
    }
}