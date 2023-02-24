package com.blog.controller;

import com.blog.dto.comment.CommentRequestDto;
import com.blog.dto.comment.CommentResponseDto;
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

    @PostMapping("/{postId}")
    public String createComment(@PathVariable("postId") UUID postId,
                                @ModelAttribute("comment") @Valid CommentRequestDto commentRequestDto,
                                BindingResult bindingResult) {
        log.info("Received request to create a new comment {}", commentRequestDto);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "redirect:/public/api/v1/comments/{postId}";
        }
        commentService.save(commentRequestDto, postId);
        return "redirect:/public/api/v1/posts/{postId}";
    }

    @GetMapping("/{commentId}/edit")
    public String updateComment(@PathVariable("commentId") UUID commentId, Model model) {
        CommentResponseDto commentResponseDto = commentService.getByCommentId(commentId);
        log.info("Received request to update a comment {}", commentResponseDto);
        model.addAttribute("comment", commentResponseDto);
        return "comment/update-comment";
    }

    @PatchMapping("/{commentId}")
    public String updateComment(@PathVariable("commentId") UUID commentId,
                                @ModelAttribute("comment") @Valid CommentResponseDto commentResponseDto,
                                BindingResult bindingResult) {
        log.info("Update request received of comment {} with new data {}", commentId, commentResponseDto);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "comment/update-comment";
        }
        commentService.update(commentId, commentResponseDto);
        return "redirect:/public/api/v1/posts";
    }

    @DeleteMapping("/{postId}/{commentId}")
    public String deleteComment(@PathVariable("postId") UUID postId, @PathVariable("commentId") UUID commentId) {
        log.info("Received request to delete a comment {}", commentId);
        commentService.delete(commentId);
        return "redirect:/public/api/v1/posts/{postId}";
    }
}