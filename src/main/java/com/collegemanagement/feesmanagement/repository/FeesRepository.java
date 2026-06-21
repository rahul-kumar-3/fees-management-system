package com.collegemanagement.feesmanagement.repository;

import com.collegemanagement.feesmanagement.entity.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Integer> {
}
