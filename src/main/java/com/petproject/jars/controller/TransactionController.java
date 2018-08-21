package com.petproject.jars.controller;

import com.petproject.jars.model.Category;
import com.petproject.jars.model.Jar;
import com.petproject.jars.model.Transaction;
import com.petproject.jars.service.CategoryService;
import com.petproject.jars.service.JarService;
import com.petproject.jars.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private JarService jarService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/transactions")
    public Transaction addTransaction(@Valid @RequestBody Transaction transaction,
                                      @RequestParam(value = "jarId") Long jarId,
                                      @RequestParam(value = "categoryId") Long categoryId
    ){
        Jar jar=jarService.getJar(jarId);
        Category category = categoryService.getCategory(categoryId);
        transaction.setCategory(category);
        transaction.setJar(jar);
        return transactionService.addTransaction(transaction);
    }

    @DeleteMapping("/transactions/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId){
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().build();
    }
}