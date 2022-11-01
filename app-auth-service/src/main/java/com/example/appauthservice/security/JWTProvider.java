package com.example.appauthservice.security;


import com.example.appdbservice.exception.RestException;
import com.example.appdbservice.exception.TokenExpiredException;
import com.example.appdbservice.utils.AppConstant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTProvider {
    @Value("${jwt.access.secret}")
    private String accessKey;
    @Value("${jwt.refresh.secret}")
    private String refreshKey;

    @Value(value = "${accessTokenLifeTime}")
    private Long accessTokenLifeTime;

    @Value(value = "${refreshTokenLifeTime}")
    private Long refreshTokenLifeTime;

    @Value(value = "${tokenSecretKey}")
    private String tokenSecretKey;


    public String generateTokenFromId(Long id, boolean isAccess) {
        Date expiredDate = new Date(System.currentTimeMillis() + (isAccess ? accessTokenLifeTime : refreshTokenLifeTime));
        return "Bearer " + Jwts
                .builder()
                .setSubject(id.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecretKey)
                .compact();
    }

    public String getIdFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(tokenSecretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException ex) {
            throw new TokenExpiredException();
        } catch (Exception e) {
            throw new RestException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    public void validateToken(String token) {
        Jwts
                .parser()
                .setSigningKey(tokenSecretKey)
                .parseClaimsJws(token);
    }

}
