package cl.vice.back.service;


import cl.vice.back.model.Account;

import java.util.List;

public interface IAccountService {

    public Account createAccount(Account account);

    public List<Account> getAllAccounts();

    public List<Account> saveAccounts();
}
