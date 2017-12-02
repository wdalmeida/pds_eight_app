package eight.ing3.esipe.fr.accountServices.authentication.service;

import dto.CredentialDto;
import dto.UserDto;
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

       //System.out.println(userEntity.getFirstName());

        return  UserDto.builder()
                .credential(CredentialDto.builder().userId(String.valueOf(userEntity.getUserId())).password(userEntity.getPassword()).build())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build() ;
     /*
      return  UserDto.builder()
                .credential(CredentialDto.builder().userId("1").password("test").build())
                .firstName("anthony")
                .lastName("perault")
                .build() ;
*/


    }
}
