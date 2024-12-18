package com.hikari.blog.request.post;

import com.hikari.blog.entity.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class UpdatePostBySlugRequest {
    @Size(min = 2, message = "minimal 2 characters")
    @NotNull
    private String title;
    @Size(min = 10, message = "minimal 10 characters")
    @NotNull
    private String body;
    @Size(min = 2, message = "minimal 2 characters")
    @NotNull
    private String slug;

    private Category category;
}
