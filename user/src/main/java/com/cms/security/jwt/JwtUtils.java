package com.cms.security.jwt;

import com.cms.type.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secret-key-plain}")
    private String secretKeyPlain;
    @Value("${jwt.expiration}")
    private Long accessTokenExpTime;
    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpTime;


    // create JWT Token
    public String createToken(Authentication authentication, Role role) {

        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put("role", role.name());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(accessTokenExpTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(signingKeyWithHMACSHA())
                .compact();
    }

    public String createRefreshToken(String token) {
        Claims claims = getClaims(token);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime refreshTokenValidity = now.plusSeconds(refreshTokenExpTime);

        return Jwts.builder()
                .setSubject(claims.getSubject())
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(refreshTokenValidity.toInstant()))
                .signWith(signingKeyWithHMACSHA())
                .compact();

    }

    private Key signingKeyWithHMACSHA() {
        return Keys.hmacShaKeyFor(secretKeyPlain.getBytes(StandardCharsets.UTF_8));
    }


    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKeyWithHMACSHA()).build().parseClaimsJws(token).getBody();

    }

    private Key signingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyPlain);

//        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰에서 사용자 이름 추출
    public String extractAccountName(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKeyWithHMACSHA())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰에서 역할 추출
    public String getRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKeyWithHMACSHA())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    // 토큰에서 email 추출
    public String getEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKeyWithHMACSHA())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(signingKeyWithHMACSHA())
                    .build()
                    .parseClaimsJws(token);

            Date expiration = claims.getBody().getExpiration();
            Date now = new Date();
            return !now.after(expiration);// 토큰이 유효하면 true 반환
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is wrong: {}", e.getMessage());
        }
        return false;
    }


}
