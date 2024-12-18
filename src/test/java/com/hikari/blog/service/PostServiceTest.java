package com.hikari.blog.service;

import com.hikari.blog.entity.Post;
import com.hikari.blog.mapper.PostMapper;
import com.hikari.blog.repository.PostRepository;
import com.hikari.blog.request.post.CreatePostRequest;
import com.hikari.blog.response.post.CreatePostResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PostServiceTest {

    @Autowired
    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    private AutoCloseable mock;
    @BeforeEach
    void beforeEach() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void AfterEach() throws Exception {
        mock.close();
    }

    @Test
    void createPost_givenValid_shouldReturnOk() {
        CreatePostRequest postRequest = new CreatePostRequest();
        postRequest.setTitle("post title");
        postRequest.setSlug("post slug");


        Post post = PostMapper.INSTANCE.mapToCreatePostRequest(postRequest);
        post.setCommentCount(0L);
        post.setCreatedAt(Instant.now().getEpochSecond());
        when(postRepository.save(post)).thenReturn(post);

        CreatePostResponse postResponse = postService.createPost(postRequest);

        Assertions.assertNotNull(postResponse);
        Assertions.assertEquals(postResponse.getCommentCount(), 0);
        Assertions.assertEquals(postResponse.getSlug(), "post slug");  ;

        verify(postRepository, times(1)).save(post);
    }
}
