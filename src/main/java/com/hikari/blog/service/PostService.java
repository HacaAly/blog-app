package com.hikari.blog.service;

import com.hikari.blog.entity.Post;
import com.hikari.blog.exception.ApiException;
import com.hikari.blog.mapper.PostMapper;
import com.hikari.blog.repository.PostRepository;
import com.hikari.blog.request.post.CreatePostRequest;
import com.hikari.blog.request.post.GetPostBySlugRequest;
import com.hikari.blog.request.post.GetPostRequest;
import com.hikari.blog.request.post.UpdatePostBySlugRequest;
import com.hikari.blog.response.post.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    @Autowired
    PostRepository postRepository;

    public GetPostResponse getPostBySlug(GetPostBySlugRequest request){
        Post post = postRepository
                .findFirstBySlugAndIsDeleted(request.getSlug(), false)
                .orElseThrow(() -> new ApiException("not found", HttpStatus.NOT_FOUND));
        return PostMapper.INSTANCE.mapToGetPostResponse(post);

    }

    public CreatePostResponse createPost(CreatePostRequest request) {

        Post post = PostMapper.INSTANCE.mapToCreatePostRequest(request);
        post.setCommentCount(0L);
        post.setCreatedAt(Instant.now().getEpochSecond());
        post = postRepository.save(post);

        return PostMapper.INSTANCE.mapToCreatePostRequest(post);

    }

    public UpdatePostBySlugResponse updatePostBySlug(String slug, UpdatePostBySlugRequest request) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(slug, false)
                        .orElseThrow(() -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setSlug(request.getSlug());
        post.setCategory(request.getCategory());
        postRepository.save(post);

        return PostMapper.INSTANCE.mapToUpdatePostBySlugResponse(post);
    }

    public DeletePostByIdResponse deletePostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setDeleted(true);
        postRepository.save(post);
        return DeletePostByIdResponse.builder().id(id).build();
    }

    public PublishPostResponse publishPost(Integer id) {
        Post post = postRepository.findByIdAndIsDeleted(id, false).orElseThrow(
                () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        post.setPublished(true);
        post.setPublishedAt(Instant.now().getEpochSecond());
        postRepository.save(post);
        return PublishPostResponse.builder().publishAt(post.getPublishedAt()).build();
    }

    public List<GetPostResponse> getPosts(GetPostRequest request) {
        List<Post> posts = postRepository.findByIsDeleted(false);
        List<GetPostResponse> responses = new ArrayList<>();
        posts.forEach(post -> responses.add(PostMapper.INSTANCE.mapToGetPostResponse(post)));
        return responses;
    }
}
