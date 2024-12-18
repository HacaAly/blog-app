package com.hikari.blog.controller;

import com.hikari.blog.request.comment.CreateCommentRequest;
import com.hikari.blog.request.comment.GetCommentsRequest;
import com.hikari.blog.response.comment.CreateCommentResponse;
import com.hikari.blog.response.comment.GetCommentResponse;
import com.hikari.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/comments")
public class CommentPublicController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getComments(@RequestParam String postSlug,
                                                @RequestParam(required = false, defaultValue = "0") Integer limit,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageNo) {
        GetCommentsRequest request = GetCommentsRequest.builder()
                .postSlug(postSlug)
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return commentService.getComments(request);
    }

    @PostMapping
    public ResponseEntity<CreateCommentResponse> createComment(@Valid @RequestBody CreateCommentRequest comment){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(comment));
    }



}

