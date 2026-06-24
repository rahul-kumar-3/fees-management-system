package com.collegemanagement.feesmanagement.controller;

import com.collegemanagement.feesmanagement.entity.Fees;
import com.collegemanagement.feesmanagement.services.FeesServices;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FeesController {
    private FeesServices feesServices;

    @PostMapping("/{sid}/fees")
    public Fees insertFees(@PathVariable Integer sid, @RequestBody Fees fees){
        return feesServices.insertFees(sid, fees);
    }

    @GetMapping("/fees")
    public List<Fees> fetchAllFees(){
        return feesServices.fetchAllFees();
    }

    @GetMapping("fees/{id}")
    public Fees fetchFeesDetailsById(@PathVariable Integer id){
        return feesServices.fetchFeesById(id);
    }

    @PutMapping("fees/{id}")
    public Fees updateFeesDetails(@PathVariable Integer id, @RequestBody Fees fees){
        return feesServices.updateFeesDetails(id, fees);
    }

    @DeleteMapping("fees/{id}")
    public void removeFeesDetails(@PathVariable Integer id){
        feesServices.removeFeesEntry(id);
    }

    @GetMapping("/fees/{startDate}/{endDate}")
    public List<Fees> fetchFeesBetweenDates(@PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate){
        return feesServices.fetchFeesBetweenDates(startDate, endDate);
    }
}
