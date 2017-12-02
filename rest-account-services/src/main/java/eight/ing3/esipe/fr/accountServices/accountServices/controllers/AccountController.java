package eight.ing3.esipe.fr.accountServices.accountServices.controllers;

import dto.AccountDto;
import eight.ing3.esipe.fr.accountServices.accountServices.services.AccountService;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    private final AccountService accountService;

   @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get(HttpServletRequest request) throws IOException {

        /*String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(test);*/
      System.out.println("[attribut requete]"+request.getAttribute("claims"));

        Claims c = (Claims) request.getAttribute("claims");
System.out.println("claim "+c.getSubject());

String userId = c.getSubject();



        final List<AccountDto> accountList = accountService.getAllAccount(userId);
        //accountList.get(0);
        return (!accountList.isEmpty()) ?
                new ResponseEntity<>(accountList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/ping")
    public ResponseEntity<String> ping() {

        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
