package be.technofutur.moonname.api.utils;

import be.technofutur.moonname.api.model.user.UserContext;
import be.technofutur.moonname.dl.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    private final SecretKey secretKey;
    private final long accessValidity;
    private final long refreshValidity;

    public JwtUtils(@Value("${jwt.secret}") String secret,
                    @Value("${jwt.access-validity}") long accessValidity,
                    @Value("${jwt.refresh-validity}") long refreshValidity) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessValidity = accessValidity;
        this.refreshValidity = refreshValidity;
    }

    public String generateAccess(User user) {
        return generate(user, "access", accessValidity);
    }

    public String generateRefresh(User user) {
        return generate(user, "refresh", refreshValidity);
    }

    private String generate(User user, String type, long validity) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("id", user.getId())
                .claim("role", user.getRole().name())
                .claim("type", type)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(secretKey)
                .compact();
    }

    public UserContext getUser(String token) {
        Claims c = parse(token);
        if (!"access".equals(c.get("type", String.class))) throw new JwtException("Mauvais type de token");
        return new UserContext(c.get("id", Integer.class), c.getSubject(), c.get("role", String.class));
    }

    public String getRefreshUsername(String token) {
        Claims c = parse(token);
        if (!"refresh".equals(c.get("type", String.class))) throw new JwtException("Refresh token invalide");
        return c.getSubject();
    }

    private Claims parse(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
}
