package com.hikari.blog.controller;

import com.hikari.blog.exception.ApiException;
import com.hikari.blog.request.comment.GetCommentsRequest;
import com.hikari.blog.response.comment.GetCommentResponse;
import com.hikari.blog.service.CommentService;
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
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc 
public class CommentPublicControllerTests {
    @Autowired
    @InjectMocks
    CommentPublicController controller;

    @Mock
    CommentService commentService;

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
    void getComments_givenOneComment_shouldReturnOne() {

        GetCommentsRequest request = GetCommentsRequest.builder()
                .postSlug("slug1")
                .pageNo(0)
                .limit(10)
                .build();

        GetCommentResponse getCommentResponse = new GetCommentResponse();
        getCommentResponse.setName("cahya");
        getCommentResponse.setEmail("cahya@example.com");
        getCommentResponse.setBody("first comment!");
        getCommentResponse.setId(1);

        List<GetCommentResponse> actualCommentResponse =
                List.of(getCommentResponse);
        when(commentService.getComments(request)).thenReturn(actualCommentResponse);

        List<GetCommentResponse> commentResponses = controller.getComments("slug1", 0, 10);

        Assertions.assertNotNull(commentResponses);
        Assertions.assertEquals(commentResponses.size(), 1);
        Assertions.assertEquals(commentResponses.getFirst().getId(), 1);
        Assertions.assertEquals(commentResponses.getFirst().getBody(), "first comment!");
    }

    @Test
    void getComments_givenPostInvalid_shouldThrowError() {

        GetCommentsRequest request = GetCommentsRequest.builder()
                .postSlug("slug1")
                .pageNo(0)
                .limit(10)
                .build();

        when(commentService.getComments(request))
                .thenThrow(new ApiException("post not found", HttpStatus.NOT_FOUND));

        Assertions.assertThrows(ApiException.class,() -> controller
                .getComments("slug1",0, 10));
    }
}

