package eight.ing3.esipe.fr.accountServices.accountServices.services;

import dto.AccountDto;
import dto.OperationDto;
import dto.UserDto;
import eight.ing3.esipe.fr.accountServices.GenericException;
import entity.AccountEntity;
import entity.OperationEntity;

import java.util.List;

public interface IAccountService {
    UserDto getUserById(String userId) throws GenericException;

    AccountDto getAccountById(String accountNumber) throws GenericException;

    List<OperationDto> transactionEntitiesToTransactionDtoList(List<OperationEntity> transactionEntities);

    List<AccountDto> accountEntitiesToAccountDtoList(List<AccountEntity> accountEntities);

    List<AccountDto> getAllAccount(String userId) throws GenericException;

    List<OperationDto> getTransactions(String account_number) throws GenericException;
}
