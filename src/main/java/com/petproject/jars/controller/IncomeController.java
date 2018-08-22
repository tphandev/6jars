package com.petproject.jars.controller;

import com.petproject.jars.exception.ResourceNotFoundException;
import com.petproject.jars.model.Category;
import com.petproject.jars.model.Income;
import com.petproject.jars.service.CategoryService;
import com.petproject.jars.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/incomes")
    public Income addIncome(@Valid @RequestBody Income income,
                            @RequestParam(value = "categoryId") Long categoryId){
        Category category= categoryService.getCategory(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found with id: "+categoryId));
        income.setCategory(category);
        return incomeService.addIncome(income);

    }
}
