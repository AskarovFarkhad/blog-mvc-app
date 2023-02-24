package com.blog.service;

import com.blog.dao.TagDao;
import com.blog.dto.tag.TagRequestDto;
import com.blog.dto.tag.TagResponseDto;
import com.blog.mapper.TagMapper;
import com.blog.model.Tag;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TagService {

    private final TagDao tagDao;

    private final TagMapper mapper;

    private final ConverterResultSet converterResultSet;

    @Autowired
    public TagService(TagDao tagDao, TagMapper mapper, ConverterResultSet converterResultSet) {
        this.tagDao = tagDao;
        this.mapper = mapper;
        this.converterResultSet = converterResultSet;
    }

    public int save(TagRequestDto tagRequestDto, UUID postId) {
        Tag tag = mapper.toTagItem(tagRequestDto);
        return tagDao.save(postId, tag);
    }

    public TagResponseDto getById(UUID tagId) {
        return converterResultSet.convertSetToTagItemDto(tagDao.getById(tagId));
    }

    public void update(UUID tagId, TagResponseDto dto) {
        TagResponseDto tagResponseDto = getById(tagId);
        tagResponseDto.setName(dto.getName());
        tagDao.update(mapper.toTagItem(tagResponseDto));
    }

    public void delete(UUID tagId) {
        tagDao.delete(tagId);
    }
}