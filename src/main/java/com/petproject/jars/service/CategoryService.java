package com.petproject.jars.service;

import com.petproject.jars.exception.ResourceNotFoundException;
import com.petproject.jars.model.Category;
import com.petproject.jars.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){

        return categoryRepository.findAll();
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category addCategory (Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory (Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory (Long id){
        categoryRepository.deleteById(id);
    }
}
