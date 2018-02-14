package eight.ing3.esipe.fr.accountServices.services;



import dto.AccountDto;
import dto.OperationDto;
import eight.ing3.esipe.fr.accountServices.GenericException;

import java.util.List;

public interface IAccountService {

    List<AccountDto> getAllAccount(String UserId) throws GenericException;
    List<OperationDto> getTransactions(String account_number) throws GenericException;
}
