package com.hikari.blog.controller;

import com.hikari.blog.request.post.GetPostBySlugRequest;
import com.hikari.blog.request.post.GetPostRequest;
import com.hikari.blog.response.post.*;
import com.hikari.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/posts")
public class PostPublicController {

    @Autowired
    PostService postService;

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping
    public List<GetPostResponse> getPosts(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                          @RequestParam(required = false, defaultValue = "10") Integer limit){
        // todo : only published post
        GetPostRequest request = GetPostRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return postService.getPosts(request);
    }

    @GetMapping("/{slug}")
    public GetPostResponse getPostBySlug(@Valid @PathVariable String slug){
        // todo : only published post
        GetPostBySlugRequest request = GetPostBySlugRequest.builder().slug(slug).build();
        return postService.getPostBySlug(request);
    }
}
