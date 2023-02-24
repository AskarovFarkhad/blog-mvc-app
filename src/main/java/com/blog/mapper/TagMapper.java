package com.blog.mapper;

import com.blog.dto.tag.TagRequestDto;
import com.blog.dto.tag.TagResponseDto;
import com.blog.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {Tag.class, TagResponseDto.class, TagRequestDto.class},
        imports = {UUID.class})
public interface TagMapper {

    @Mapping(target = "tagId", expression = "java(UUID.randomUUID())")
    Tag toTagItem(TagRequestDto tagRequestDto);

    Tag toTagItem(TagResponseDto tagResponseDto);
}