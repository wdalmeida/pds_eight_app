package eight.ing3.esipe.fr.service;

import dto.UserDto;
import eight.ing3.esipe.fr.service.IUserLoginService;
import dto.CredentialDto;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserLoginService implements IUserLoginService {

    private final UserRepository userRepository;

    @Autowired
    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto checkCredentials(CredentialDto credentials) {

       UserEntity userEntity = userRepository.findOne(Long.parseLong(credentials.getUserId()));

       

        return  UserDto.builder()
                .credential(CredentialDto.builder().userId(String.valueOf(userEntity.getUserId())).password(userEntity.getPassword()).build())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build() ;



    }
}
