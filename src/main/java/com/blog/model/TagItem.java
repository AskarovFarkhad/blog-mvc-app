package com.blog.model;

import java.util.Objects;
import java.util.UUID;

public class TagItem {

    private UUID tagId;

    private String name;

    public TagItem() {
    }

    public TagItem(String name) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagItem tagItem = (TagItem) o;
        return tagId.equals(tagItem.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId);
    }

    @Override
    public String toString() {
        return "TagItem{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                '}';
    }
}