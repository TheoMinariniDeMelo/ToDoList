package configs.securityConfiguration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import models.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class JwtService {
    @Value("${spring.jwt.security-token}")
    private String token;

    public Algorithm algorithm() {
        return Algorithm.HMAC256(token);
    }

    public String generateToken(UserModel user) {
        try {
            return JWT.create()
                    .withIssuer("api")//Emissor do token
                    .withSubject(user.getEmail())//Usuario
                    .withExpiresAt(getInstantForToken())//Tempo em que o TOKEN será expirado
                    .sign(algorithm());
        } catch (JWTCreationException error) {
            throw new RuntimeException(error.getMessage() + "--------------------");
        }
    }

    public String validToken(String token) {
        try {
            return JWT.require(algorithm())
                    .withIssuer("api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException error) {
            throw new RuntimeException(error.getMessage());
        }
    }

    public Instant getInstantForToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
