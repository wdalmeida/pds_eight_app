package eight.ing3.esipe.fr.service;

import dto.UserDto;
import io.jsonwebtoken.Claims;

public interface IJwtService {

    String createToken(UserDto userDto);

    Claims verifyToken(String token);

}
