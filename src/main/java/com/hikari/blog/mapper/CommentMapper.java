package com.hikari.blog.mapper;

import com.hikari.blog.entity.Comment;
import com.hikari.blog.request.comment.CreateCommentRequest;
import com.hikari.blog.response.comment.CreateCommentResponse;
import com.hikari.blog.response.comment.GetCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment mapFromCreateCommentRequest(CreateCommentRequest createPostRequest);

    CreateCommentResponse mapToCreateCommentResponse(Comment comment);

    GetCommentResponse MapToGetCommentResponse(Comment comment);
}
