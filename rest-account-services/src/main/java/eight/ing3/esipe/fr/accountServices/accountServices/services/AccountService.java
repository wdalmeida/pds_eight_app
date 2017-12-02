package eight.ing3.esipe.fr.accountServices.accountServices.services;


import dto.AccountDto;
import dto.CredentialDto;
import dto.UserDto;
import entity.AccountEntity;
import entity.UserEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {


    private final Mapper dozer;

  /*  @Override
    public List<AccountDto> getAllAccount(String UserId) {
        return null;
    }
*/
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;



    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.dozer = new DozerBeanMapper();
    }


    public UserDto getUserById(String userId){
        System.out.print("userId entity"+ userId);
        UserEntity userEntity = userRepository.findOne(Long.parseLong(userId));
        System.out.print(userEntity.getFirstName());
       return UserDto.builder()
                .credential(CredentialDto.builder().userId(String.valueOf(userEntity.getUserId())).password(userEntity.getPassword()).build())
        .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .accountDtoList(accountEntitiesToAccountDtoList(userEntity.getAccounts()))
                .build() ;
        //return dozer.map(userEntity,UserDto.class);
    }

    public List<AccountDto> accountEntitiesToAccountDtoList(List<AccountEntity> accountEntities){
        return accountEntities
                .stream()
                .map(
                        a -> AccountDto.builder()
                                .accountNumber(String.valueOf(a.getAccountNumber()))
                                .type(a.getType())
                                .balance(a.getBalance())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAllAccount(String userId) {

        System.out.println("valeur userId dans service"+userId);
        return getUserById(userId).getAccountDtoList();
    }



}
