package com.hikari.blog.request.comment;

import com.hikari.blog.entity.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
public class
CreateCommentRequest {

    @Size(min = 2, max = 100, message = "name must between 2 and 100 Characters")
    @NotNull
    private String name;

    @Size(min = 2, max = 100)
    @Email
    @NotNull
    private String email;

    @NotNull
    private Post post;

    @NotNull
    @Size(min = 2, max = 10_000)
    private String body;
}
