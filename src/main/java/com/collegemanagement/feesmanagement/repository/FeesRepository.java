package com.collegemanagement.feesmanagement.repository;

import com.collegemanagement.feesmanagement.entity.Fees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeesRepository extends JpaRepository<Fees, Integer> {
    List<Fees> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}
