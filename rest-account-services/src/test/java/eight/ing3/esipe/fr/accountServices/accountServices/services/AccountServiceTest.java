package eight.ing3.esipe.fr.accountServices.accountServices.services;

import dto.AccountDto;
import dto.AccountType;
import dto.CredentialDto;
import dto.UserDto;
import eight.ing3.esipe.fr.accountServices.GenericException;
import entity.AccountEntity;
import entity.UserEntity;
import org.junit.Before;
import org.junit.Test;
import repository.AccountRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest {


    private AccountService accountService;
    private UserRepository userRepositoryMock;

    @Before
    public void setUp(){
        userRepositoryMock = mock(UserRepository.class);
        accountService = new AccountService(userRepositoryMock);
    }

    @Test
    public void getUserById() throws Exception {

        AccountEntity accountEntity = new AccountEntity("1",AccountType.LivretA.name(),100);
        List<AccountEntity> accountEntities = new ArrayList<>();
        accountEntities.add(accountEntity);
        UserEntity userEntity = new UserEntity("test","test","test",accountEntities);

        UserDto model  = UserDto.builder()
                .credential(CredentialDto.builder().userId(String.valueOf(userEntity.getUserId())).password(userEntity.getPassword()).build())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .accountDtoList(accountEntitiesToAccountDtoList(userEntity.getAccounts()))
                .build();



        when(userRepositoryMock.findOne(1L)).thenReturn(userEntity);

        UserDto actual = accountService.getUserById("1");

        verify(userRepositoryMock,times(1)).findOne(1L);
        verifyNoMoreInteractions(userRepositoryMock);

        assertThat(actual,is(model));
    }

    @Test(expected = GenericException.class)
    public void getUserById_UserNotFound_ShouldThrowAnException() throws GenericException {
        when(userRepositoryMock.findOne(1L)).thenReturn(null);

        accountService.getUserById("1");

        verify(userRepositoryMock, times(1)).findOne(1L);
        verifyNoMoreInteractions(userRepositoryMock);


    }



    public List<AccountDto> accountEntitiesToAccountDtoList(List<AccountEntity> accountEntities){
        return accountEntities
                .stream()
                .map(
                        a -> AccountDto.builder()
                                .accountNumber(String.valueOf(a.getAccountNumber()))
                                .type(AccountType.valueOf(a.getType()))
                                .balance(a.getBalance())
                                .build()
                )
                .collect(Collectors.toList());
    }

}
