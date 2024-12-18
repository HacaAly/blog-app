package com.hikari.blog.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostResponse {
    private String title;
    private String body;
    private String slug;
    private String category;
    private Long createdAt;
    private Long commentCount;
}
