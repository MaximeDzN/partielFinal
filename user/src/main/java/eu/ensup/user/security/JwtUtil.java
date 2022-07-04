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

import java.util.Date;

@Configuration
@Slf4j
public class JwtUtil {

    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    //minutes to milliseconds
    private static final int EXPIRATION_TIME = 60 * 60 * 1000;
    @Value("${secret.token}")
    private static final String SECRET = "";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public Boolean validateToken(String token){
        try {
            JWT.require(ALGORITHM).build().verify(token);
            return true;
        } catch (JWTVerificationException j) {
            logger.error(j.getMessage());
            return false;
        }
    }

    public String getUsernameFromJwt(String token){
        return JWT.decode(token).getClaims().get("subject").asString();
    }

    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .withClaim("roles", String.valueOf(user.getAuthorities()))
                .sign(ALGORITHM);

    }

}
