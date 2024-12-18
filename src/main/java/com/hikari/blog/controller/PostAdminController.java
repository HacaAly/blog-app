package com.hikari.blog.controller;

import com.hikari.blog.request.post.CreatePostRequest;
import com.hikari.blog.request.post.GetPostBySlugRequest;
import com.hikari.blog.request.post.GetPostRequest;
import com.hikari.blog.request.post.UpdatePostBySlugRequest;
import com.hikari.blog.response.post.*;
import com.hikari.blog.service.PostService;
import com.hikari.blog.response.post.GetPostResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/posts")
public class PostAdminController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<GetPostResponse> getPosts(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                          @RequestParam(required = false, defaultValue = "10") Integer limit){
        GetPostRequest request = GetPostRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return postService.getPosts(request);
    }

    @GetMapping("/{slug}")
    public GetPostResponse getPostBySlug(@Valid @PathVariable String slug){
        GetPostBySlugRequest request = GetPostBySlugRequest.builder().slug(slug).build();
        return postService.getPostBySlug(request);
    }

    @PostMapping
    public CreatePostResponse createPost(@Valid @RequestBody CreatePostRequest createPostRequest){
       return postService.createPost(createPostRequest);
    }

    @PutMapping("/{slug}")
    public UpdatePostBySlugResponse updatePostByslug(@PathVariable String slug,
                                                     @Valid @RequestBody UpdatePostBySlugRequest request) {
        return postService.updatePostBySlug(slug, request);
    }

    @DeleteMapping("/{id}")
    public DeletePostByIdResponse deletePostById(@PathVariable Integer id) {
        return postService.deletePostById(id);
    }

    @PostMapping("/{id}/publish")
    public PublishPostResponse publishPost(@PathVariable Integer id) {
        return  postService.publishPost(id);
    }

}
