package eight.ing3.esipe.fr.accountServices.accountServices.services;


import dto.AccountDto;
import dto.AccountType;
import dto.CredentialDto;
import dto.UserDto;
import eight.ing3.esipe.fr.accountServices.GenericException;
import entity.AccountEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {


    private final UserRepository userRepository;



    @Autowired
    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public UserDto getUserById(String userId) throws GenericException {

        UserEntity userEntity = userRepository.findOne(Long.parseLong(userId));


        if(userEntity == null) {

                throw new GenericException("No user found with this id " + userId);
        }
        else {
            return UserDto.builder()
                    .credential(CredentialDto.builder().userId(String.valueOf(userEntity.getUserId())).password(userEntity.getPassword()).build())
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .accountDtoList(accountEntitiesToAccountDtoList(userEntity.getAccounts()))
                    .build();
        }



       }


    @Override
    public List<AccountDto> accountEntitiesToAccountDtoList(List<AccountEntity> accountEntities){
        return accountEntities
                .stream()
                .map(
                        a -> AccountDto.builder()
                                .accountNumber(a.getAccountNumber())
                                .type(AccountType.valueOf(a.getType()))
                                .balance(a.getBalance())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAllAccount(String userId) throws GenericException {


        return getUserById(userId).getAccountDtoList();

    }



}
