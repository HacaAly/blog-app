package com.hikari.blog.repository;

import com.hikari.blog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Page<Comment> findByPostId(Integer postId, Pageable pageable);

}
