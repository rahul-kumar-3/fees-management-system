package com.collegemanagement.feesmanagement.services;

import com.collegemanagement.feesmanagement.entity.Fees;

import java.util.List;

public interface FeesServices {
    Fees insertFees(Integer sid, Fees fees);
    List<Fees> fetchAllFees();
    Fees fetchFeesById(Integer id);
    Fees updateFeesDetails(Integer id, Fees fees);
    void removeFeesEntry(Integer id);
}
