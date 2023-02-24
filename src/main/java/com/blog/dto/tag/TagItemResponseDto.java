package com.blog.dto.tag;


import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class TagItemResponseDto {

    private UUID tagId;

    @NotBlank(message = "\"Tag\" field must not be empty")
    private String name;

    public TagItemResponseDto() {
    }

    public TagItemResponseDto(UUID tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public UUID getTagId() {
        return tagId;
    }

    public void setTagId(UUID tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagItemResponseDto{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                '}';
    }
}