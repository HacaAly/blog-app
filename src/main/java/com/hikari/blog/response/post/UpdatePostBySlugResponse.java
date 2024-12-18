package com.hikari.blog.response.post;

import com.hikari.blog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostBySlugResponse {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private Category category;
    private Long publishedAt;
    private Long commentCount;
}


