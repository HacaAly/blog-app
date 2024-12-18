package com.hikari.blog.controller;

import com.hikari.blog.entity.Comment;
import com.hikari.blog.entity.Post;
import com.hikari.blog.repository.CommentRepository;
import com.hikari.blog.repository.PostRepository;
import com.hikari.blog.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentAdminControllerIntegrationTests {

    private final String HEADER_NAME = "Authorization";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    JwtService jwtService;

    @BeforeEach
    void beforeEach(){
        postRepository.deleteAll();
        commentRepository.deleteAll();

        Post post = new Post();
        post.setTitle("title2");
        post.setSlug("slug1");
        post.setCommentCount(1L);
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setId(1);
        comment.setPost(post);
        comment.setName("name");
        commentRepository.save(comment);
    }

    @Test
    void createComment_givenValid_shouldReturnCreated() throws Exception {
        String jwtToken = jwtService.generateTokenByUsername("cahya");
        mockMvc.perform(get("/api/admin/comments/1")
                        .header(HEADER_NAME, "Bearer %s".formatted(jwtToken)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                                    "id": 1
                        }
                        """));
    }
}
