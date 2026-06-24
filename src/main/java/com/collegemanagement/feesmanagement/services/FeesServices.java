package com.collegemanagement.feesmanagement.services;

import com.collegemanagement.feesmanagement.entity.Fees;

import java.time.LocalDate;
import java.util.List;

public interface FeesServices {
    Fees insertFees(Integer sid, Fees fees);
    List<Fees> fetchAllFees();
    Fees fetchFeesById(Integer id);
    Fees updateFeesDetails(Integer id, Fees fees);
    void removeFeesEntry(Integer id);
    List<Fees> fetchFeesBetweenDates(LocalDate startDate, LocalDate endDate);
}
