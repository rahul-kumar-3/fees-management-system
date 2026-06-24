package com.collegemanagement.feesmanagement.controller;

import com.collegemanagement.feesmanagement.entity.Fees;
import com.collegemanagement.feesmanagement.response.ApiResponseHandler;
import com.collegemanagement.feesmanagement.services.FeesServices;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class FeesController {
    private FeesServices feesServices;

    @PostMapping("/{sid}/fees")
    public ResponseEntity<ApiResponseHandler<Fees>>insertFees(@PathVariable Integer sid, @RequestBody Fees fees){
        Fees savedFees = feesServices.insertFees(sid, fees);
        ApiResponseHandler<Fees> response = new ApiResponseHandler<>(
                true,
                "Fees payment processed successfully.",
                savedFees
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/fees")
    public ResponseEntity<ApiResponseHandler<List<Fees>>> fetchAllFees(){
        List<Fees> feesList = feesServices.fetchAllFees();
        ApiResponseHandler<List<Fees>> response = new ApiResponseHandler<>(
                true,
                "List of paid amount.",
                feesList
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("fees/{id}")
    public ResponseEntity<ApiResponseHandler<Fees>> fetchFeesDetailsById(@PathVariable Integer id){
        Fees fees = feesServices.fetchFeesById(id);
        ApiResponseHandler<Fees> response = new ApiResponseHandler<>(
                true,
                "Fees with id "+id+" found",
                fees
        );
        return new ResponseEntity<ApiResponseHandler<Fees>>(response, HttpStatus.OK);
    }

    @PutMapping("fees/{id}")
    public ResponseEntity<ApiResponseHandler<Fees>> updateFeesDetails(@PathVariable Integer id, @RequestBody Fees fees){
        Fees newFees = feesServices.updateFeesDetails(id, fees);
        ApiResponseHandler<Fees> response = new ApiResponseHandler<>(
                true,
                "Fees with id "+id+" updated.",
                newFees
        );
        return new ResponseEntity<ApiResponseHandler<Fees>>(response, HttpStatus.OK);
    }

    @DeleteMapping("fees/{id}")
    public ResponseEntity<ApiResponseHandler<Fees>> removeFeesDetails(@PathVariable Integer id){
        feesServices.removeFeesEntry(id);
        ApiResponseHandler<Fees> response = new ApiResponseHandler<>(
                true,
                "Fees with id +"+id+" removed successfuly from the server",
                null
        );
        return new ResponseEntity<ApiResponseHandler<Fees>>(response, HttpStatus.OK);
    }

    @GetMapping("/fees/{startDate}/{endDate}")
    public List<Fees> fetchFeesBetweenDates(@PathVariable("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate, @PathVariable("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate){
        return feesServices.fetchFeesBetweenDates(startDate, endDate);
    }
}
