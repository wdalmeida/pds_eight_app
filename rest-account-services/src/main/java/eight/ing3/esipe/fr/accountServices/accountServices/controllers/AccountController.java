package eight.ing3.esipe.fr.accountServices.controllers;

import dto.AccountDto;
import eight.ing3.esipe.fr.accountServices.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

        final List<AccountDto> accountList = accountService.getAllAccount("1");
        //accountList.get(0);
        return (!accountList.isEmpty()) ?
                new ResponseEntity<>(accountList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/ping")
    public ResponseEntity<String> ping() {

        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
