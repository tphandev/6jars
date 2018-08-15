package com.petproject.jars.repository;

import com.petproject.jars.model.Jar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JarRepository extends JpaRepository<Jar,Long> {

}
