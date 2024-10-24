package ru.ilcorp.neuro_test.utils.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import ru.ilcorp.neuro_test.utils.exeptions.user.IncorrectTokenException;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret; // Секретный ключ, храним в настройках приложения

    @Value("${jwt.expiration}")
    private int jwtExpirationMs; // Время жизни токена
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String uniqueUsername) {
        return Jwts.builder()
                .setSubject(uniqueUsername)
                .setIssuedAt(new Date()) // Время создания токена
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Время истечения токена
                .signWith(secretKey)
                .compact();
    }

    public Claims validateToken(String token) throws IncorrectTokenException {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)  // Используем объект SecretKey для верификации
                    .build()
                    .parseClaimsJws(token)
                    .getBody();  // Извлекаем claims из токена
        } catch (SignatureException exception){
            throw new IncorrectTokenException("Возникли проблемы со входом");
        }

    }
}
