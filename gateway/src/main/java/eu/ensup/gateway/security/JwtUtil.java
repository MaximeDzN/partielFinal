package eu.ensup.gateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class JwtUtil {
    Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value(value = "${token.secret}")
    private String secret = "";


    public Map<String, Claim> getClaims(String token){
        return JWT.decode(token).getClaims();
    }


    public Boolean validateToken(String token){
        try {
            JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
            return true;
        } catch (JWTVerificationException j) {
            logger.error(j.getMessage());
            return false;
        }
    }


}
