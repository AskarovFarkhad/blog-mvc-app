package com.blog.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "tagId")
public class TagItem {

    private Long tagId;

    private String name;
}