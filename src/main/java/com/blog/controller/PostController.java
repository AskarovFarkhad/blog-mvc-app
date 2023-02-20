package com.blog.controller;

import com.blog.dto.PostDto;
import com.blog.dto.UserDto;
import com.blog.service.PostService;
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
@RequestMapping("public/api/v1/posts")
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @PostMapping()
    public String createPost(@ModelAttribute("post") PostDto dto) {
        log.info("Received request to create a new post {}", dto);
        postService.save(dto);
        return "redirect:/public/api/v1/posts";
    }

    @GetMapping("/{postId}/edit")
    public String updatePost(@PathVariable("postId") UUID postId, Model model) {
        PostDto postDto = postService.getById(postId);
        log.info("Received request to update a post {}", postDto);
        model.addAttribute("user", postDto);
        return "post/update-post";
    }

    @PatchMapping("/{postId}")
    public String updatePost(@PathVariable("postId") UUID postId, @ModelAttribute("post") PostDto dto) {
        log.info("Update request received of post {} with new data {}", postId, dto);
        postService.update(postId, dto);
        return "redirect:/public/api/v1/posts";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable("postId") UUID postId) {
        log.info("Received request to delete a post {}", postId);
        postService.delete(postId);
        return "redirect:/public/api/v1/posts";
    }

    @GetMapping()
    public String getAllPosts(Model model) {
        log.info("Received request to get all post's");
        List<PostDto> posts = postService.getAll();
        List<UserDto> authors = userService.getAllUsers();
        model.addAttribute("posts", posts);
        model.addAttribute("authors", authors);
        model.addAttribute("post", new PostDto());
        return "post/get-all-posts";
    }
}