package cl.vice.back.service.impl;

import cl.vice.back.model.Account;
import cl.vice.back.repository.IAccountRepository;
import cl.vice.back.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        Account accountAux = new Account();
        accountAux.setId(account.getId());
        accountAux.setBalance(account.getBalance());
        accountAux.setClientId(account.getClientId());
        accountAux.setInsuranceId(account.getInsuranceId());
        accountAux = accountRepository.save(accountAux);
        return accountAux;
    }

    public List<Account> saveAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account(1, 6, 1, 15000),
                new Account(2, 3, 1, 18000),
                new Account(3, 3, 1, 135000),
                new Account(4, 2, 2, 5600),
                new Account(5, 1, 3, 23000),
                new Account(6, 2, 2, 15000),
                new Account(7, 3, 3, 45900),
                new Account(8, 3, 3, 19000),
                new Account(9, 3, 3, 51000),
                new Account(10, 1, 1, 89000),
                new Account(11, 2, 2, 1600),
                new Account(12, 3, 3, 37500),
                new Account(13, 1, 1, 19200),
                new Account(14, 3, 3, 10000),
                new Account(15, 2, 2, 5400),
                new Account(16, 1, 1, 9000),
                new Account(17, 3, 3, 13500),
                new Account(18, 1, 2, 38200),
                new Account(19, 2, 2, 17000),
                new Account(20, 3, 3, 1000),
                new Account(21, 2, 2, 600),
                new Account(22, 1, 1, 16200),
                new Account(23, 2, 2, 10000)
        );

        List<Account> savedAccounts = new ArrayList<>();

        for (Account account : accounts) {
            Account savedAccount = createAccount(account);
            savedAccounts.add(savedAccount);
        }

        return savedAccounts;
    }


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
