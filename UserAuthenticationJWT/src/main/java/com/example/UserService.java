package com.example;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final Key jwtKey;
    private final long expirationMs;

    public UserService(UserRepo userRepo,
                       @Value("${jwt.secret}") String secret,
                       @Value("${jwt.expiration}") long expirationMs) {
        this.userRepo = userRepo;
        this.jwtKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public Map<String, String> loginUser(Integer uid, String email) {
        Optional<User> optUser = userRepo.findByUidAndEmail(uid, email);

        if (optUser.isEmpty()) {
            return Map.of("error", "Invalid UID or Email");
        }

        String token = Jwts.builder()
                .setSubject(String.valueOf(uid))
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(jwtKey, SignatureAlgorithm.HS256)
                .compact();

        return Map.of("token", token);
    }


}
