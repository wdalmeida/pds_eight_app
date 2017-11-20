package fr.esipe.ing3.pds.eight.restaccountservices.controllers;

import fr.esipe.ing3.pds.eight.restaccountservices.models.AccountDto;
import fr.esipe.ing3.pds.eight.restaccountservices.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() {

        final List<AccountDto> accountList = accountService.getAllAccount();
        accountList.get(0);
        return (!accountList.isEmpty()) ?
                new ResponseEntity<>(accountList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
