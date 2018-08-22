package com.petproject.jars.service;

import com.petproject.jars.model.Jar;
import com.petproject.jars.model.Transaction;
import com.petproject.jars.model.TransactionType;
import com.petproject.jars.repository.JarRepository;
import com.petproject.jars.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JarRepository jarRepository;


    @Transactional
    public Transaction addTransaction (Transaction transaction){

        BigDecimal jarAmount;
        BigDecimal transactionAmount= transaction.getAmount();
        Jar jar= transaction.getJar();

        if(transaction.getCategory().getType().equals(TransactionType.Outcome)){
            transactionAmount=transactionAmount.multiply(new BigDecimal(-1));
        }

        jarAmount=jar.getAmount().add(transactionAmount);
        jar.setAmount(jarAmount);
        jarRepository.save(jar);
        return transactionRepository.save(transaction);
    }


    @Transactional
    public void deleteTransaction (Transaction transaction){
        BigDecimal jarAmount;
        Jar jar=transaction.getJar();
        jarAmount= jar.getAmount().subtract(transaction.getAmount());
        jar.setAmount(jarAmount);
        jarRepository.save(jar);
        transactionRepository.delete(transaction);
    }

    public Optional<Transaction> getTransaction(Long id){
        return transactionRepository.findById(id);
    }

}
