package eight.ing3.esipe.fr.accountServices.authentication.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

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

  

    public AuthenticationFilter() {
       

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
            Claims claims = Jwts.parser().setSigningKey("MTIzc2Z2MWU2djFlcnYxOThlcjF2NXYxOWU4YjFlNjViMTY1ZWYxYnY5OGU0ZmI".getBytes()).parseClaimsJws(jwtToken).getBody();
            System.out.println(claims.getSubject());
            httpServletRequest.setAttribute("userId",claims.getSubject());
            httpServletRequest.setAttribute("claims",claims);
            System.out.println(httpServletRequest.toString());
        }
        catch (ExpiredJwtException exception) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.expired");
            return;
        } catch (JwtException exception) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "error.jwt.invalid");
            return;
        }

     
        filterChain.doFilter(servletRequest,servletResponse);



    }
}
