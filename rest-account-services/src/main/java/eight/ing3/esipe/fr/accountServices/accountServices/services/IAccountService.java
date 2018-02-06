package eight.ing3.esipe.fr.accountServices.accountServices.services;



import dto.AccountDto;
import dto.TransactionDto;
import eight.ing3.esipe.fr.accountServices.GenericException;

import java.util.List;

public interface IAccountService {

    List<AccountDto> getAllAccount(String UserId) throws GenericException;
    List<TransactionDto> getTransactions(String account_number) throws GenericException;
}
