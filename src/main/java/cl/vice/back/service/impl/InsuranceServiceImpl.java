package cl.vice.back.service.impl;

import cl.vice.back.model.Account;
import cl.vice.back.model.Client;
import cl.vice.back.model.Insurance;
import cl.vice.back.repository.IInsuranceRepository;
import cl.vice.back.service.IAccountService;
import cl.vice.back.service.IClientService;
import cl.vice.back.service.IInsuranceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InsuranceServiceImpl implements IInsuranceService {

    private final IInsuranceRepository insuranceRepository;
    private final IAccountService accountService;


    @Autowired
    public InsuranceServiceImpl(IInsuranceRepository insuranceRepository, IAccountService accountService) {
        this.insuranceRepository = insuranceRepository;
        this.accountService = accountService;
    }

    public Insurance createInsurance(Insurance insurance) {
        Insurance insuranceAux = new Insurance();
        insuranceAux.setId(insurance.getId());
        insuranceAux.setName(insurance.getName());
        insuranceAux = insuranceRepository.save(insuranceAux);
        return insuranceAux;
    }

    public List<Insurance> createInsurances() {
        List<Insurance> insurances = Arrays.asList(
                new Insurance(1, "SEGURO APV"),
                new Insurance(2, "SEGURO DE VIDA"),
                new Insurance(3, "SEGURO COMPLEMENTARIO DE SALUD")
        );

        List<Insurance> savedInsurances = new ArrayList<>();

        for (Insurance insurance : insurances) {
            Insurance savedInsurance = createInsurance(insurance);
            savedInsurances.add(savedInsurance);
        }

        return savedInsurances;
    }

    public List<Integer> insuranceSortedByHighestBalance() {
        List<Account> allAccounts = accountService.getAllAccounts();

        Map<Integer, Integer> insuranceBalances = allAccounts.stream()
                .collect(Collectors.groupingBy(
                        Account::getInsuranceId,
                        Collectors.summingInt(Account::getBalance)
                ));

        return insuranceBalances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    public String getInsuranceNameById(Integer insuranceId) {
        return insuranceRepository.findById(insuranceId)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
