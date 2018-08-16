package com.petproject.jars.controller;

import com.petproject.jars.model.Category;
import com.petproject.jars.model.Jar;
import com.petproject.jars.service.JarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class JarController {

    @Autowired
    private JarService jarService;

    @GetMapping("/jars")
    public List<Jar> getJars(){
        List<Jar> jars= jarService.getAllJars();
        //init data when jars table is empty
        if(jars.isEmpty()){
            jars.add(new Jar("Necessity Account",
                    "This account is for managing your everyday expenses and bills.This would include things like your rent, mortgage, utilities, bills, taxes,food, clothes, etc. Basically it includes anything that you need to live, the necessities.",
                    55,new BigDecimal(0)));
            jars.add(new Jar("Play Account",
                    "PLAY money is spent every month on purchases you wouldn’t normally nmake. The purpose of this jar is to nurture yourself. You could purchase nan expensive bottle of wine at dinner, get a massage or go on a nweekend getaway. Play can be anything your heart desires. You and a nspouse can each receive your own play money, and not even ask what the other person spends it on!",
                    10,new BigDecimal(0)));
            jars.add(new Jar("Financial Freedom Account",
                    "This is your golden goose. This jar is your ticket to financial freedom.The money that you put into this jar is used for investments and building your passive income streams. You never spend this money.The only time you would spend this money is once you become financially free. Even then you would only spend the returns on your investment.Never spend the principal.",
                    10,new BigDecimal(0)));
            jars.add(new Jar("Education Account",
                    "Money in this jar is meant to further your education and personal growth. An investment in yourself is a great way to use your money.You are your most valuable asset. Never forget this. Education money can be used to purchase books, CD’s, courses or anything else that has educational value.",
                    10,new BigDecimal(0)));
            jars.add(new Jar("Long-term saving for spending Account",
                    "Money in this jar is for bigger, nice-to-have purchases. Use the money for vacations, extravagances, a plasma TV, contingency fund, your children's education etc. A small monthly contribution can go a long way. You may have more than one LTS jar. If you have more than one LTS, divide the 10% between the jars according to your priorities.",
                    10,new BigDecimal(0)));
            jars.add(new Jar("Give Account",
                    "Money in this jar is for giving away. Use the money for family and friends on birthdays, special occasions and holidays. You can also give away your time as opposed to giving away money. You could house sit for a neighbor, take a friend’s dog for a walk or volunteer in your community or for your favorite charity.",
                    5,new BigDecimal(0)));
            jars =jarService.updateJars(jars);
        }
        return jars;
    }

    @PutMapping("/jars")
    public List<Jar> updateJars(@Valid @RequestBody List<Jar> jars){
        try {
            return jarService.updateJars(jars);
        }catch (Exception ex){
            throw ex;
        }

    }

}
