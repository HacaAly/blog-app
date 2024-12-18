package com.hikari.blog.controller;

import com.hikari.blog.request.category.CreateCategoryRequest;
import com.hikari.blog.request.category.GetCategoriesRequest;
import com.hikari.blog.request.category.UpdateCategoryRequest;
import com.hikari.blog.response.category.CreateCategoryResponse;
import com.hikari.blog.response.category.DeleteCategoryResponse;
import com.hikari.blog.response.category.GetCategoryResponse;
import com.hikari.blog.response.category.UpdateCategoryResponse;
import com.hikari.blog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryAdminController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<GetCategoryResponse>> getCategories(
            @RequestParam(required = false, defaultValue = "0") Integer limit,
            @RequestParam(required = false, defaultValue = "10") Integer pageNo) {
        GetCategoriesRequest request = GetCategoriesRequest.builder()
                .pageNo(pageNo)
                .limit(limit)
                .build();
        return ResponseEntity.ok(categoryService.getCategories(request, false));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryResponse> getCategoryById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CreateCategoryResponse> createCategory(
            @Valid @RequestBody CreateCategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.createCategory(request));
    }

    @PutMapping
    public ResponseEntity<UpdateCategoryResponse> updateCategory(
            @Valid @RequestBody UpdateCategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                categoryService.updateCategory(request));
    }

    @DeleteMapping
    public ResponseEntity<DeleteCategoryResponse> deleteCategoryById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.deletedById(id));
    }


}

