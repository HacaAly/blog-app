package com.hikari.blog.controller;

import com.hikari.blog.request.comment.CreateCommentRequest;
import com.hikari.blog.request.comment.GetCommentByIdRequest;
import com.hikari.blog.request.comment.GetCommentsRequest;
import com.hikari.blog.response.comment.CreateCommentResponse;
import com.hikari.blog.response.comment.GetCommentResponse;
import com.hikari.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/comments")
public class CommentAdminController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getComments(@RequestParam(required = false) String postSlug,
                                                @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                @RequestParam(required = false, defaultValue = "10") Integer limit) {
        GetCommentsRequest request = GetCommentsRequest.builder()
                .postSlug(postSlug)
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return commentService.getComments(request);
    }

    @GetMapping("/{id}")
    public GetCommentResponse getComment(@PathVariable Integer id){
        GetCommentByIdRequest request = GetCommentByIdRequest.builder().id(id).build();
        return commentService.getComment(request);
    }

    @PostMapping
    public CreateCommentResponse createComment(@Valid @RequestBody CreateCommentRequest comment){
        return commentService.createComment(comment);
    }



}

