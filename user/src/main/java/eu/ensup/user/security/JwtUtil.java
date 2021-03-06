package eu.ensup.user.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import eu.ensup.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
public class JwtUtil {

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    //minutes to milliseconds
    private static final int EXPIRATION_TIME = 60 * 60 * 1000;
    @Value("${token.secret}")
    private String secret = "";

    public Boolean validateToken(String token){
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return true;
        } catch (JWTVerificationException j) {
            logger.error(j.getMessage());
            return false;
        }
    }

    public String getUsernameFromJwt(String token){
        return JWT.decode(token).getClaims().get("sub").asString();
    }

    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        List<String> strings = new ArrayList<>();
        for (GrantedAuthority grantedAuthority :user.getAuthorities()) {
            strings.add(grantedAuthority.toString());
        }
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .withClaim("roles",String.join(",",strings))
                .withClaim("id",user.getId())
                .sign(Algorithm.HMAC256(secret));

    }

}
