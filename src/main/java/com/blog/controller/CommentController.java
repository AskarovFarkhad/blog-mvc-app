package com.blog.controller;

import com.blog.dto.CommentRequestDto;
import com.blog.dto.CommentResponseDto;
import com.blog.service.CommentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("public/api/v1/comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public String createComment(@ModelAttribute("comment") @Valid CommentRequestDto commentRequestDto,
                             BindingResult bindingResult) {
        log.info("Received request to create a new comment {}", commentRequestDto);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}",  bindingResult.getAllErrors());
            return "redirect:/public/api/v1/posts";
        }
        commentService.save(commentRequestDto);
        return "redirect:/public/api/v1/posts";
    }

    @GetMapping("/{commentId}/edit")
    public String updateComment(@PathVariable("commentId") UUID commentId, Model model) {
        CommentResponseDto commentResponseDto = commentService.getById(commentId);
        log.info("Received request to update a comment {}", commentResponseDto);
        model.addAttribute("post", commentResponseDto);
        return "post/update-comment";
    }

    @PatchMapping("/{commentId}")
    public String updateComment(@PathVariable("commentId") UUID commentId,
                             @ModelAttribute("comment") @Valid CommentRequestDto commentRequestDto,
                             BindingResult bindingResult) {
        log.info("Update request received of comment {} with new data {}", commentId, commentRequestDto);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}",  bindingResult.getAllErrors());
            return "post/update-comment";
        }
        commentService.update(commentId, commentRequestDto);
        return "redirect:/public/api/v1/posts";
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable("commentId") UUID commentId) {
        log.info("Received request to delete a comment {}", commentId);
        commentService.delete(commentId);
        return "redirect:/public/api/v1/posts";
    }
}