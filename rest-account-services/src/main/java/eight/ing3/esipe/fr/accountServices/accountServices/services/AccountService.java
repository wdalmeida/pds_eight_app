package eight.ing3.esipe.fr.accountServices.accountServices.services;


import dto.*;
import eight.ing3.esipe.fr.accountServices.GenericException;
import entity.AccountEntity;
import entity.TransactionEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {


    private final UserRepository userRepository;
    private final AccountRepository accountRepository;



    @Autowired
    public AccountService(UserRepository userRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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

       public AccountDto getAccountById(String accountNumber) throws GenericException {

        AccountEntity accountEntity = accountRepository.findOne(accountNumber);

        if(accountEntity == null ){
            throw new GenericException("No account found with this id " + accountNumber);
        }
        else {

            return AccountDto.builder()
                    .accountNumber(accountEntity.getAccountNumber())
                    .balance(accountEntity.getBalance())
                    .type(AccountType.valueOf(accountEntity.getType()))
                    .transactionDtoList(transactionEntitiesToTransactionDtoList(accountEntity.getTransactions()))
                    .build();
        }

       }


    public List<TransactionDto> transactionEntitiesToTransactionDtoList(List<TransactionEntity> transactionEntities){

        return transactionEntities
                .stream()
                .map(
                        t -> TransactionDto.builder()
                        .transactionId(t.getTransactionId())
                        .date(t.getDate())
                        .description(t.getDescription())
                        .amount(t.getAmount())
                        .build()
                )
                .collect(Collectors.toList());

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

    @Override
    public List<TransactionDto> getTransactions(String account_number) throws GenericException {

        return getAccountById(account_number).getTransactionDtoList();
    }

}
