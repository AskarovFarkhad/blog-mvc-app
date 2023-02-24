package com.blog.controller;

import com.blog.dto.tag.TagRequestDto;
import com.blog.dto.tag.TagResponseDto;
import com.blog.service.TagService;
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
@RequestMapping("public/api/v1/tags")
public class TagController {

    private static final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/{postId}")
    public String createComment(@PathVariable("postId") UUID postId,
                                @ModelAttribute("tag") @Valid TagRequestDto tagRequestDto,
                                BindingResult bindingResult) {
        log.info("Received request to create a new tag {}", tagRequestDto);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "redirect:/public/api/v1/tags/{postId}";
        }
        tagService.save(tagRequestDto, postId);
        return "redirect:/public/api/v1/posts/{postId}";
    }

    @GetMapping("/{tagId}/edit")
    public String updateComment(@PathVariable("tagId") UUID tagId, Model model) {
        TagResponseDto tagResponseDto = tagService.getById(tagId);
        log.info("Received request to update a tag {}", tagResponseDto);
        model.addAttribute("tag", tagResponseDto);
        return "tag/update-tag";
    }

    @PatchMapping("/{tagId}")
    public String updateComment(@PathVariable("tagId") UUID tagId,
                                @ModelAttribute("comment") @Valid TagResponseDto tagResponseDto,
                                BindingResult bindingResult) {
        log.info("Update request received of comment {} with new data {}", tagId, tagResponseDto);
        if (bindingResult.hasErrors()) {
            log.error("Data not validated {}", bindingResult.getAllErrors());
            return "tag/update-tag";
        }
        tagService.update(tagId, tagResponseDto);
        return "redirect:/public/api/v1/posts";
    }

    @DeleteMapping("/{postId}/{tagId}")
    public String deleteComment(@PathVariable("postId") UUID postId, @PathVariable("tagId") UUID tagId) {
        log.info("Received request to delete a comment {}", tagId);
        tagService.delete(tagId);
        return "redirect:/public/api/v1/posts/{postId}";
    }
}