package com.petproject.jars.controller;

import com.petproject.jars.exception.ResourceNotFoundException;
import com.petproject.jars.model.Category;
import com.petproject.jars.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategory(@PathVariable Long categoryId){
        try {
            return categoryService.getCategory(categoryId);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Category not found with id "+categoryId);
        }
    }

    @PostMapping("/categories")
    public Category createCategory(@Valid @RequestBody Category category){
        try {
            return categoryService.addCategory(category);
        }catch (Exception ex){
            throw ex;
        }

    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId,
            @Valid @RequestBody Category category){
        try {
            category.setId(categoryId);
            return categoryService.updateCategory(category);
        }catch (Exception ex){
            throw ex;
        }

    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok().build();
        }catch (Exception ex){
            throw ex;
        }
    }
}
