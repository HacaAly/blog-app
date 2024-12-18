package com.hikari.blog.controller;

import com.hikari.blog.entity.Post;
import com.hikari.blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentPublicControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;
    @Test
    void createComment_givenValid_shouldReturnCreated() throws Exception {
        Post post = new Post();
        post.setTitle("title2");
        post.setSlug("slug1");
        post.setCommentCount(1L);
        postRepository.save(post);
        mockMvc.perform(
                post("/api/public/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "name",
                                    "email": "cahyaalhafiizh21@gmail.com",
                                    "post": {
                                        "slug": "slug1"
                                    },
                                    "body": "keren sekali blog ini"
                                }
                                """)
        )
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                                    "name": "name",
                                    "email": "cahyaalhafiizh21@gmail.com",
                                    "post": {
                                        "slug": "slug1"
                                    },
                                    "body": "keren sekali blog ini"
                                }
                        """));
    }
}
