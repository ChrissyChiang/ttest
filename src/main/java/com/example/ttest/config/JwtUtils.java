package com.example.ttest.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtils {

    private static final String SECRET = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

    public String generateToken(String userName){
        Date now = new Date();
        Date exp = new Date(now.getTime()+60*60*1000);//一小時後過期

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, SECRET )
                .compact();
    }

    public String getUserNameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
