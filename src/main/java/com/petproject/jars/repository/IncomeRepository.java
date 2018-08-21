package com.petproject.jars.repository;

import com.petproject.jars.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {

}
