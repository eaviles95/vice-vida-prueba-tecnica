package cl.vice.back.controller;

import cl.vice.back.exception.InvalidInputException;
import cl.vice.back.model.Account;
import cl.vice.back.service.IAccountService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequestMapping(value = "/account")
@Api(value = "Account controller", produces = "application/json")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping(value = "getAllAccounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Account.class.getSimpleName());
        }
    }

    @PostMapping(value = "createAccount")
    public ResponseEntity<Account> createAccount(Account account) {
        try {
            return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Account.class.getSimpleName());
        }
    }

    @PostMapping(value = "saveAccounts")
    public ResponseEntity<List<Account>> saveAccounts() {
        try {
            return new ResponseEntity<>(accountService.saveAccounts(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new InvalidInputException(Account.class.getSimpleName());
        }
    }

}
