package eight.ing3.esipe.fr.accountServices.authentication.controller;


import dto.CredentialDto;
import dto.UserDto;
import eight.ing3.esipe.fr.accountServices.authentication.service.JwtService;
import eight.ing3.esipe.fr.accountServices.authentication.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserLoginController {

    private final UserLoginService userLoginService;
    private final JwtService jwtService;


    @Autowired
    public UserLoginController(UserLoginService userLoginService, JwtService jwtService) {
        this.userLoginService = userLoginService;
        this.jwtService = jwtService;

    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST/*,consumes = "application/json;charset=UTF-8"*/)
    public ResponseEntity<?> signInAttempt(@RequestBody CredentialDto credential){


        UserDto userDto = userLoginService.checkCredentials(credential);


      if(userDto.getCredential().getPassword().equals(credential.getPassword())){
          String jwtToken = jwtService.createToken(userDto);
          return new ResponseEntity<>(jwtToken, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }
}
