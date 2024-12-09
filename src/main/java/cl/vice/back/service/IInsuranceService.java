package cl.vice.back.service;


import cl.vice.back.model.Insurance;

import java.util.List;
import java.util.Map;

public interface IInsuranceService {

    public Insurance createInsurance(Insurance insurance);

    public List<Insurance> getAllInsurances();

    public List<Insurance> createInsurances();

    public List<Integer> insuranceSortedByHighestBalance();

    public String getInsuranceNameById(Integer insuranceId);

}