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

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JarRepository jarRepository;

    @Transactional
    public Transaction addTransaction (Transaction transaction){

        BigDecimal jarAmount= new BigDecimal(0);
        BigDecimal transactionAmount= transaction.getAmount();

        Jar jar= jarRepository.findById(transaction.getJar().getId()).get();

        if(transaction.getCategory().getType().equals(TransactionType.Outcome)){
            transactionAmount=transactionAmount.multiply(new BigDecimal(-1));
        }

        jarAmount=jar.getAmount().add(transactionAmount);
        jar.setAmount(jarAmount);
        jarRepository.save(jar);

        return transactionRepository.save(transaction);
    }


    public void deleteTransaction (Long id){
        BigDecimal jarAmount= new BigDecimal(0);
        Transaction transaction= transactionRepository.findById(id).get();
        Jar jar=transaction.getJar();
        jarAmount= jar.getAmount().subtract(transaction.getAmount());
        jar.setAmount(jarAmount);
        jarRepository.save(jar);
        transactionRepository.delete(transaction);
    }

}
