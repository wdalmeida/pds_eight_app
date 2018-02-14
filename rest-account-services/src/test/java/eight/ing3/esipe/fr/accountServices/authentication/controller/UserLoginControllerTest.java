package eight.ing3.esipe.fr.accountServices.authentication.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.AccountDto;
import dto.CredentialDto;
import dto.UserDto;
import eight.ing3.esipe.fr.accountServices.TestContext;
import eight.ing3.esipe.fr.accountServices.WebAppContext;
import eight.ing3.esipe.fr.accountServices.authentication.service.JwtService;
import eight.ing3.esipe.fr.accountServices.authentication.service.UserLoginService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserLoginController.class)
@ContextConfiguration(classes = {TestContext.class,WebAppContext.class})
@WebAppConfiguration
public class UserLoginControllerTest {

	
    private MockMvc mockMvc;

    @Autowired
    private UserLoginService userLoginServiceMock;
    @Autowired
    private JwtService jwtServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {

        Mockito.reset(userLoginServiceMock);
        Mockito.reset(jwtServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void signInAttemptWork() throws Exception {

        CredentialDto trueCredentialDto = CredentialDto.builder().userId("1").password("password").build();
        
     
        

        UserDto userDto  = UserDto.builder()
                .credential(CredentialDto.builder().userId("1").password("password").build())
                .firstName("test")
                .lastName("test")
                .build();
        ObjectMapper mapper = new ObjectMapper();
        
        RequestBuilder rq = MockMvcRequestBuilders.post("/auth").accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(trueCredentialDto)).contentType(MediaType.APPLICATION_JSON);

        System.out.println("test1"+mapper.writeValueAsString(trueCredentialDto));
        when(userLoginServiceMock.checkCredentials(trueCredentialDto)).thenReturn(userDto);
        when(jwtServiceMock.createToken(userDto)).thenReturn("fakeToken");


        mockMvc.perform(rq).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("fakeToken"));


        verify(userLoginServiceMock, times(1)).checkCredentials(trueCredentialDto);
        verifyNoMoreInteractions(userLoginServiceMock);
    }

    @Test
    public void signInAttemptFail() throws Exception {

        CredentialDto falseCredentialDto = CredentialDto.builder().userId("1").password("xyz").build();

        UserDto userDto  = UserDto.builder()
                .credential(CredentialDto.builder().userId("1").password("password").build())
                .firstName("test")
                .lastName("test")
                .accountDtoList(Collections.emptyList())
                .build();
        ObjectMapper mapper = new ObjectMapper();

        
        when(userLoginServiceMock.checkCredentials(any(CredentialDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(falseCredentialDto))).andDo(print())
                .andExpect(status().isUnauthorized());


        verify(userLoginServiceMock, times(1)).checkCredentials(falseCredentialDto);
        verifyNoMoreInteractions(userLoginServiceMock);


    }

}