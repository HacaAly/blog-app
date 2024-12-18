package com.hikari.blog.service;

import com.hikari.blog.entity.Comment;
import com.hikari.blog.entity.Post;
import com.hikari.blog.exception.ApiException;
import com.hikari.blog.mapper.CommentMapper;
import com.hikari.blog.repository.CommentRepository;
import com.hikari.blog.repository.PostRepository;
import com.hikari.blog.request.comment.CreateCommentRequest;
import com.hikari.blog.request.comment.GetCommentByIdRequest;
import com.hikari.blog.request.comment.GetCommentsRequest;
import com.hikari.blog.response.comment.CreateCommentResponse;
import com.hikari.blog.response.comment.GetCommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    public List<GetCommentResponse> getComments(GetCommentsRequest request) {
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getPostSlug(), false).orElseThrow(
                        () -> new ApiException("post not found", HttpStatus.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(request.getPageNo(), request.getLimit());
        List<Comment> comments = commentRepository.findByPostId(post.getId(), pageRequest).getContent();
        List<GetCommentResponse> responses = new ArrayList<>();
        comments.forEach(comment -> responses.add(CommentMapper.INSTANCE.MapToGetCommentResponse(comment)));
        return responses;
    }

    public GetCommentResponse getComment(GetCommentByIdRequest request){
        Comment comment = commentRepository.findById(request.getId()).orElseThrow(
                () -> new ApiException("comment not found", HttpStatus.NOT_FOUND));
        return CommentMapper.INSTANCE.MapToGetCommentResponse(comment);
    }

    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest request){
        Post post = postRepository.findFirstBySlugAndIsDeleted(request.getPost().getSlug(), false)
                .orElseThrow(() -> new ApiException("post not found", HttpStatus.NOT_FOUND));

        Comment comment = CommentMapper.INSTANCE.mapFromCreateCommentRequest(request);

        comment.setCreatedAt(Instant.now().getEpochSecond());
        comment.getPost().setId(post.getId());
        commentRepository.save(comment);

        post.setCommentCount(post.getCommentCount()+1);
        postRepository.save(post);

        return CommentMapper.INSTANCE.mapToCreateCommentResponse(comment);
    }
}
