package com.hikari.blog.controller;

import com.hikari.blog.entity.Post;
import com.hikari.blog.repository.PostRepository;
import com.hikari.blog.service.JwtService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostAdminControllerIntegrationTests {

    private final String HEADER_NAME = "Authorization";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JwtService jwtService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void beforeEach() {
        postRepository.deleteAll();

        Post post = new Post();
        post.setSlug("calon_programmer");
        post.setTitle("123");
        post.setId(1);
        postRepository.save(post);
    }

    @AfterEach
    void afterEach() {
        postRepository.deleteAll();
    }

    @Test
    void createPost_givenSlugIsAlreadyUsed_shouldReturnBadRequest() throws Exception {
        List<Post> postsBefore = (List<Post>) postRepository.findAll();

        String jwtToken = jwtService.generateTokenByUsername("cahya");
        mockMvc.perform(post("/api/admin/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HEADER_NAME, "Bearer %s".formatted(jwtToken))
                        .content("""
                                {
                                    "title": "123",
                                    "slug": "calon_programmer",
                                    "body": "calon programmer java handal"
                                }
                                """))
                .andExpect(status().isBadRequest());

        List<Post> postAfter = (List<Post>) postRepository.findAll();
        Assertions.assertEquals(postsBefore.size(), postAfter.size());
    }
}
