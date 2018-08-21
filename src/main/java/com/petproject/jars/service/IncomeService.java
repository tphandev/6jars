package com.petproject.jars.service;

import com.petproject.jars.model.Income;
import com.petproject.jars.model.Jar;
import com.petproject.jars.model.Transaction;
import com.petproject.jars.repository.IncomeRepository;
import com.petproject.jars.repository.JarRepository;
import com.petproject.jars.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IncomeService{

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private JarRepository jarRepository;

    @Transactional
    public Income addIncome(Income income){

        List<Jar> jars= jarRepository.findAll();
        Income savedIncome= incomeRepository.save(income);
        for (Jar jar:jars) {
            BigDecimal jarAmount= savedIncome.getAmount().multiply(new BigDecimal(jar.getPercentage())).divide(new BigDecimal(100));
            Transaction transaction= new Transaction(jarAmount,savedIncome.getCategory(),jar,savedIncome,savedIncome.getNote(),savedIncome.getIncomeDate());
            transactionService.addTransaction(transaction);
        }
        return savedIncome;
    }
}
