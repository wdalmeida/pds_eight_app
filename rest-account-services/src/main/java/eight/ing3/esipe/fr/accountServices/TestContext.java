package eight.ing3.esipe.fr.accountServices;

import eight.ing3.esipe.fr.accountServices.accountServices.services.AccountService;
import eight.ing3.esipe.fr.accountServices.authentication.service.JwtService;
import eight.ing3.esipe.fr.accountServices.authentication.service.UserLoginService;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

public class TestContext {

    @Bean
    public AccountService accountService() {
        return Mockito.mock(AccountService.class);
    }

    @Bean
    public UserLoginService userLoginService() {
        return Mockito.mock(UserLoginService.class);
    }

    @Bean
    public JwtService jwtServicee() {
        return Mockito.mock(JwtService.class);
    }
}
