package com.hikari.blog.request.post;

import com.hikari.blog.entity.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CreatePostRequest {

    @Size(min = 2, message = "minimal 2 characters")
    @NotNull
    private String title;
    @Size(min = 10, message = "minimal 10 characters")
    @NotNull
    private String body;
    private Category category;
    @Size(min = 2, message = "minimal 2 characters")
    @NotNull
    private String slug;

}
