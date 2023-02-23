package com.blog.dto;


import jakarta.validation.constraints.NotBlank;

public class TagItemRequestDto {

    @NotBlank(message = "\"Tag\" field must not be empty")
    private String name;

    public TagItemRequestDto() {
    }

    public TagItemRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagItemRequestDto{" +
                "name='" + name + '\'' +
                '}';
    }
}