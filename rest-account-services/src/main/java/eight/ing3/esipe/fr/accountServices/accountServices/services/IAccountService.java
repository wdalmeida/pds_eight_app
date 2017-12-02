package eight.ing3.esipe.fr.accountServices.services;

import dto.AccountDto;
import dto.UserDto;
import eight.ing3.esipe.fr.accountServices.GenericException;
import entity.AccountEntity;

import java.util.List;

public interface IAccountService {
    UserDto getUserById(String userId) throws GenericException;

    List<AccountDto> accountEntitiesToAccountDtoList(List<AccountEntity> accountEntities);

    List<AccountDto> getAllAccount(String userId) throws GenericException;
}
