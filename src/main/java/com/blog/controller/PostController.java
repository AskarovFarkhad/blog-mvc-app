package com.blog.controller;

import com.blog.dto.PostRequestDto;
import com.blog.dto.PostResponseDto;
import com.blog.dto.UserDto;
import com.blog.service.PostService;
import com.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("public/api/v1/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping()
    public String createPost(@ModelAttribute("post") PostRequestDto postRequestDto) {
        log.info("Received request to create a new post {}", postRequestDto);
        postService.save(postRequestDto);
        return "redirect:/public/api/v1/posts";
    }

    @GetMapping("/{postId}/edit")
    public String updatePost(@PathVariable("postId") UUID postId, Model model) {
        PostResponseDto postResponseDto = postService.getById(postId);
        log.info("Received request to update a post {}", postResponseDto);
        model.addAttribute("post", postResponseDto);
        return "post/update-post";
    }

    @PatchMapping("/{postId}")
    public String updatePost(@PathVariable("postId") UUID postId,
                             @ModelAttribute("post") PostResponseDto postResponseDto) {
        log.info("Update request received of post {} with new data {}", postId, postResponseDto);
        postService.update(postId, postResponseDto);
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
        List<PostResponseDto> posts = postService.getAll();
        List<UserDto> authors = userService.getAllUsers();
        model.addAttribute("posts", posts);
        model.addAttribute("authors", authors);
        model.addAttribute("post", new PostRequestDto());
        return "post/get-all-posts";
    }
}