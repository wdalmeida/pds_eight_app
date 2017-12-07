package eight.ing3.esipe.fr.accountServices.accountServices.controllers;

import dto.AccountDto;
import eight.ing3.esipe.fr.accountServices.GenericException;
import eight.ing3.esipe.fr.accountServices.accountServices.services.AccountService;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(value="accounts",method = RequestMethod.GET)
    public ResponseEntity<?> get(HttpServletRequest request) throws IOException, GenericException {



        String userId = (String) request.getAttribute("userId");



        final List<AccountDto> accountList = accountService.getAllAccount(userId);
        return (!accountList.isEmpty() && accountList != null ) ?
                new ResponseEntity<>(accountList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }



}
