package eight.ing3.esipe.fr.controller;




import dto.CredentialDto;
import dto.UserDto;
import entity.UserEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import eight.ing3.esipe.fr.service.JwtService;
import eight.ing3.esipe.fr.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserLoginController {

    private final UserLoginService userLoginService;
    private final JwtService jwtService;
    private final Mapper mapper;

    @Autowired
    public UserLoginController(UserLoginService userLoginService, JwtService jwtService) {
        this.userLoginService = userLoginService;
        this.jwtService = jwtService;
        this.mapper = new DozerBeanMapper();
    }

    @RequestMapping("/auth")
    public ResponseEntity signIn(@RequestBody CredentialDto credential){

        UserDto userDto = userLoginService.checkCredentials(credential);



     // UserDto userDto =  mapper.map(userLoginService.checkCredentials(credential), UserDto.class);


      if(userDto.getCredential().getPassword().equals(credential.getPassword())){
          String jwtToken = jwtService.createToken(userDto);
          return ResponseEntity.ok().body(jwtToken);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());


    }
}
