package com.hikari.blog.repository;

import com.hikari.blog.entity.Category;
import com.hikari.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    List<Category> findByIsDeleted(boolean isDeleted);

}
