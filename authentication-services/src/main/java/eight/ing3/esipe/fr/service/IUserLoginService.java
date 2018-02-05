package eight.ing3.esipe.fr.service;

import dto.CredentialDto;
import dto.UserDto;
import entity.UserEntity;

public interface IUserLoginService {

    UserDto checkCredentials(CredentialDto credentials);
}
