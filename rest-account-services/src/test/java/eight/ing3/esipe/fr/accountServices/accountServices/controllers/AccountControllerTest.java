package eight.ing3.esipe.fr.accountServices.accountServices.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.AccountDto;
import dto.AccountType;
import eight.ing3.esipe.fr.accountServices.TestContext;
import eight.ing3.esipe.fr.accountServices.WebAppContext;
import eight.ing3.esipe.fr.accountServices.accountServices.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class,WebAppContext.class})
@WebAppConfiguration
public class AccountControllerTest {

	
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {

        Mockito.reset(accountServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnAccountDtoList() throws Exception {

        AccountDto a1 = AccountDto.builder()
                .accountNumber("1")
                .type(AccountType.valueOf("PEL"))
                .balance(100)
                .build();

        AccountDto a2 = AccountDto.builder()
                .accountNumber("2")
                .type(AccountType.valueOf("LivretA"))
                .balance(200)
                .build();

        when(accountServiceMock.getAllAccount("1")).thenReturn(Arrays.asList(a1, a2));

        mockMvc.perform(get("/account").requestAttr("userId","1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(expectedResult()));


        verify(accountServiceMock, times(1)).getAllAccount("1");
        verifyNoMoreInteractions(accountServiceMock);


    }

    @Test
    public void shouldReturn204code() throws Exception {
        mockMvc.perform(get("/account").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }


    public String expectedResult() throws JsonProcessingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        final List<AccountDto> response = new ArrayList<>();

        AccountDto a1 =    AccountDto.builder()
                .accountNumber("1")
                .type(AccountType.valueOf("PEL"))
                .balance(100)
                .build();

        AccountDto a2 =    AccountDto.builder()
                .accountNumber("2")
                .type(AccountType.valueOf("LivretA"))
                .balance(200)
                .build();
        response.add(a1);
        response.add(a2);

        return mapper.writeValueAsString(response);

    }





}