package com.hikari.blog.mapper;

import com.hikari.blog.entity.Post;
import com.hikari.blog.request.post.CreatePostRequest;
import com.hikari.blog.response.post.CreatePostResponse;
import com.hikari.blog.response.post.GetPostResponse;
import com.hikari.blog.response.post.UpdatePostBySlugResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post mapToCreatePostRequest(CreatePostRequest createPostRequest);

    CreatePostResponse mapToCreatePostRequest(Post post);

    GetPostResponse mapToGetPostResponse(Post post);

    UpdatePostBySlugResponse mapToUpdatePostBySlugResponse(Post post);

    

}
