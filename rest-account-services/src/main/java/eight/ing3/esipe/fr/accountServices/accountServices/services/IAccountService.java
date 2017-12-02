package eight.ing3.esipe.fr.accountServices.accountServices.services;



import dto.AccountDto;

import java.util.List;

public interface IAccountService {

    List<AccountDto> getAllAccount(String UserId);
}
