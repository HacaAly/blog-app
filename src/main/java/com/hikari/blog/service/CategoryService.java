package com.hikari.blog.service;

import com.hikari.blog.entity.Category;
import com.hikari.blog.entity.Post;
import com.hikari.blog.exception.ApiException;
import com.hikari.blog.mapper.CategoryMapper;
import com.hikari.blog.mapper.PostMapper;
import com.hikari.blog.repository.CategoryRepository;
import com.hikari.blog.repository.PostRepository;
import com.hikari.blog.request.category.CreateCategoryRequest;
import com.hikari.blog.request.category.GetCategoriesRequest;
import com.hikari.blog.request.category.UpdateCategoryRequest;
import com.hikari.blog.response.category.CreateCategoryResponse;
import com.hikari.blog.response.category.DeleteCategoryResponse;
import com.hikari.blog.response.category.GetCategoryResponse;
import com.hikari.blog.response.category.UpdateCategoryResponse;
import com.hikari.blog.response.post.GetPostResponse;
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
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    public List<GetCategoryResponse> getCategories(GetCategoriesRequest request, boolean b) {
        List<Category> categories = categoryRepository.findByIsDeleted(false);
        List<GetCategoryResponse> responses = new ArrayList<>();
        categories.forEach(category -> responses.add(CategoryMapper.INSTANCE.mapToGetCategoryResponse((Category) categories)));
        return responses;
    }

    public List<GetCategoryResponse> getCategories(GetCategoriesRequest request) {
        return null;
    }

    public GetCategoryResponse getCategoryById(Integer id) {
        return null;
    }

    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        Category category = CategoryMapper.INSTANCE.map(request);
        category.setCreatedAt(Instant.now().getEpochSecond());
        categoryRepository.save(category);

        return CategoryMapper.INSTANCE.mapToCreateCategoryResponse(category);
    }

    public UpdateCategoryResponse updateCategory(UpdateCategoryRequest request) {
        return null;
    }

    public DeleteCategoryResponse deletedById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException("not found", HttpStatus.NOT_FOUND));

        Long numberOfPosts = postRepository.countByCategory(category);

        if (numberOfPosts != 0) {
            throw new ApiException("posts still exist in this category", HttpStatus.BAD_REQUEST);
        }

        category.setDeleted(true);
        categoryRepository.save(category);

        return DeleteCategoryResponse.builder().id(category.getId()).build();
    }
}
