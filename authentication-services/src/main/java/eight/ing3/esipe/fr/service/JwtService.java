package eight.ing3.esipe.fr.service;
import eight.ing3.esipe.fr.service.IJwtService;
import dto.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService implements IJwtService {


    @Override
    public String createToken(UserDto user) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,"MTIzc2Z2MWU2djFlcnYxOThlcjF2NXYxOWU4YjFlNjViMTY1ZWYxYnY5OGU0ZmI".getBytes())
                .setClaims(buildUserClaims(user))
                .setIssuedAt(new Date())
                .compact();

    }

    @Override
    public Claims verifyToken(String token) {
        return Jwts.parser().setSigningKey("MTIzc2Z2MWU2djFlcnYxOThlcjF2NXYxOWU4YjFlNjViMTY1ZWYxYnY5OGU0ZmI".getBytes()).parseClaimsJws(token).getBody();
    }

    private Claims buildUserClaims(UserDto user){
        Claims claims = new DefaultClaims();

        claims.setSubject(user.getCredential().getUserId());
        claims.put("firstName",user.getFirstName());
        claims.put("lastName",user.getLastName());

        return claims;
    }
}
