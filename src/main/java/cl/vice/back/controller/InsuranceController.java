package cl.vice.back.controller;

import cl.vice.back.exception.InvalidInputException;
import cl.vice.back.model.Insurance;
import cl.vice.back.service.IInsuranceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequestMapping(value = "/insurance")
@Api(value = "Insurance controller", produces = "application/json")
public class InsuranceController {

    @Autowired
    private IInsuranceService insuranceService;

    @GetMapping(value = "getAllInsurances")
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        try {
            return new ResponseEntity<>(insuranceService.getAllInsurances(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Insurance.class.getSimpleName());
        }
    }

    @GetMapping(value = "insuranceSortedByHighestBalance")
    public ResponseEntity<List<Integer>> insuranceSortedByHighestBalance() {
        try {
            return new ResponseEntity<>(insuranceService.insuranceSortedByHighestBalance(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Insurance.class.getSimpleName());
        }
    }



    @PostMapping(value = "createInsurance")
    public ResponseEntity<Insurance> createInsurances(Insurance insurance) {
        try {
            return new ResponseEntity<>(insuranceService.createInsurance(insurance), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Insurance.class.getSimpleName());
        }
    }

    @PostMapping(value = "createInsurances")
    public ResponseEntity<List<Insurance>> createInsurances() {
        try {
            return new ResponseEntity<>(insuranceService.createInsurances(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Insurance.class.getSimpleName());
        }
    }
}
