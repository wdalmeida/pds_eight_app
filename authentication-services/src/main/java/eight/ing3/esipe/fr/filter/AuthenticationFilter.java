package eight.ing3.esipe.fr.filter;

import eight.ing3.esipe.fr.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import eight.ing3.esipe.fr.service.IJwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthenticationFilter extends GenericFilterBean {

   // private final JwtService jwtService;


    public AuthenticationFilter(/*JwtService jwtService*/) {
       // this.jwtService = jwtService;

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        final Optional<String> token = Optional.ofNullable((httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)));

        if(!token.isPresent()) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.token.isabsent");
            return;
        }

        System.out.println("["+token.get()+"]");

        String jwtToken = token.get().substring("Bearer".length()+1);

        System.out.println("["+jwtToken+"]");

        try {
            Claims claims = /*jwtService.verifyToken(jwtToken)*/  Jwts.parser().setSigningKey("MTIzc2Z2MWU2djFlcnYxOThlcjF2NXYxOWU4YjFlNjViMTY1ZWYxYnY5OGU0ZmI".getBytes()).parseClaimsJws(jwtToken).getBody();
            System.out.println(claims.getSubject());
            httpServletRequest.setAttribute("claims",claims);
        }
        catch (ExpiredJwtException exception) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.expired");
            return;
        } catch (JwtException exception) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.invalid");
            return;
        }

       // httpServletResponse.addHeader("Authorization","Bearer "+jwtToken);

      /*  Authentication authentication =

        SecurityContextHolder.getContext().setAuthentication();*/



        filterChain.doFilter(servletRequest,servletResponse);



    }
}
