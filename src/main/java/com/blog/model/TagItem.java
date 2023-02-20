package com.blog.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "tagId")
public class TagItem {

    private UUID tagId;

    private String name;
}