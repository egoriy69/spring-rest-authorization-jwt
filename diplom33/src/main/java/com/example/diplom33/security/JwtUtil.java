package com.example.diplom33.security;



import com.example.diplom33.models.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JwtUtil {
    @Value("${jwt_secret}")
    private String secret;
    @Value("${jwt_lifetime}")
    private Duration jwtLifeTime;

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        claims.put("roles", roles);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime()+jwtLifeTime.toMillis());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(issuedDate)
                .expiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
//                        Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
    }




    public String getUserName(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<Role> getRoles(String token){
//        return getAllClaimsFromToken(token).get("roles", List.class);
        List<String> roleNames = getAllClaimsFromToken(token).get("roles", List.class);

        // Преобразование имен ролей в объекты Role
        return roleNames.stream().map(roleName -> {
            Role role = new Role();
            role.setName(roleName);
            return role;
        }).collect(Collectors.toList());
    }

    private Claims getAllClaimsFromToken(String token){
//        return Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token).getBody();
        return Jwts.parser().setSigningKey(secret.getBytes()).build().parseSignedClaims(token).getBody();


    }
}
