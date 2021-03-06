package com.petproject.jars.service;

import com.petproject.jars.model.Jar;
import com.petproject.jars.repository.JarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JarService {

    @Autowired
    private JarRepository jarRepository;

    //Make sure sum of jars is 100%
    private boolean vaildJar(List<Jar> jars){
        int sumPercent=0;
        for (Jar jar:jars) {
            sumPercent=sumPercent+jar.getPercentage();
        }
        if (sumPercent!=100){
            return false;
        }
        else return true;
    }

    public List<Jar> getAllJars(){

        return jarRepository.findAll();
    }

    public List<Jar> updateJars(List<Jar> jars){
        if(vaildJar(jars)){
           return jarRepository.saveAll(jars);
        }else return null;
    }

}
