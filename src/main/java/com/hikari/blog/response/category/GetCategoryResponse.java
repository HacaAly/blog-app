package com.hikari.blog.response.category;

import com.hikari.blog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryResponse {
    private Integer Id;
    private String name;
    private String slug;

    public static GetCategoryResponse fromCategory(Category category) {
        return GetCategoryResponse.builder()
                .Id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .build();
    }

}
