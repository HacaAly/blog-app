package com.hikari.blog.mapper;

import com.hikari.blog.entity.Category;
import com.hikari.blog.entity.Post;
import com.hikari.blog.request.category.CreateCategoryRequest;
import com.hikari.blog.response.category.CreateCategoryResponse;
import com.hikari.blog.response.category.GetCategoryResponse;
import com.hikari.blog.response.post.GetPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category map(CreateCategoryRequest request);

    GetCategoryResponse mapToGetCategoryResponse(Category category);

    CreateCategoryResponse mapToCreateCategoryResponse(Category category);
}
