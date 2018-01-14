package eight.ing3.esipe.fr.accountServices.authentication.service;

import dto.CredentialDto;
import dto.UserDto;

public interface IUserLoginService {

    UserDto checkCredentials(CredentialDto credentials);
}
