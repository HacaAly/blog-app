package com.hikari.blog.response.post;

import com.hikari.blog.entity.Category;
import com.hikari.blog.entity.Post;
import com.hikari.blog.response.category.GetCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPostResponse {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private List<GetCategoryResponse> category;
    private Long publishedAt;
    private Long commentCount;

    public static GetPostResponse fromPostAndCategory(Post post, List<GetCategoryResponse> categories) {
        return GetPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .slug(post.getSlug())
                .category(categories)
                .publishedAt(post.getPublishedAt())
                .commentCount(post.getCommentCount())
                .build();
    }

}
