package com.whiteblue.backend.security.jwt;

import com.whiteblue.backend.config.ApplicationProperties;
import com.whiteblue.backend.domain.user.UserRepository;
import com.whiteblue.backend.security.oAuth.OAuthUser;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Component
public class JWTProvider {
    private final ApplicationProperties applicationProperties;

    private final UserRepository userRepository;

    private Long accessTokenExpire;

    private Long refreshTokenExpire;

    private String secretKey;

    @PostConstruct
    public void init() {
        final Long HOUR = 1000L * 60 * 60;
        accessTokenExpire = applicationProperties.getAccessTokenExpire() * HOUR;
        refreshTokenExpire = applicationProperties.getRefreshTokenExpire() * HOUR;
        secretKey = applicationProperties.getSecretKey();
    }

    public String createAccessToken(Authentication authentication) {
        Date now = new Date();
        OAuthUser user = (OAuthUser) authentication.getPrincipal();

        return Jwts.builder()
                   .setSubject("access_token")
                   .setIssuer("Whiteblue")
                   .setIssuedAt(now)
                   .claim("id", user.getId())
                   .claim("username", user.getUsername())
                   .claim("name", user.getName())
                   .claim("role", authentication.getAuthorities()
                                                .stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.joining(",")))
                   .setExpiration(new Date(now.getTime() + accessTokenExpire))
                   .signWith(SignatureAlgorithm.HS512, secretKey)
                   .compact();
    }

    @Transactional
    public String createRefreshToken(Authentication authentication) {
        Date now = new Date();
        String refreshToken = Jwts.builder()
                                  .setSubject("refresh_token")
                                  .setIssuer("Whiteblue")
                                  .setIssuedAt(now)
                                  .setExpiration(new Date(now.getTime() + refreshTokenExpire))
                                  .signWith(SignatureAlgorithm.HS512, secretKey)
                                  .compact();
        OAuthUser user = (OAuthUser) authentication.getPrincipal();
        userRepository.updateRefreshToken(user.getId(), refreshToken);

        return refreshToken;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("role")
                                    .toString()
                                    .split(","))
                      .map(SimpleGrantedAuthority::new)
                      .collect(Collectors.toList());

        OAuthUser principal = OAuthUser.builder()
                                       .id(((Integer) claims.get("id")).longValue())
                                       .username((String) claims.get("username"))
                                       .name((String) claims.get("name"))
                                       .authorities(authorities)
                                       .build();

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException exception) {
            log.info("만료된 토큰입니다.");
        } catch (UnsupportedJwtException exception) {
            log.info("지원되지 않는 토큰입니다.");
        } catch (IllegalStateException exception) {
            log.info("토큰이 잘못되었습니다");
        } catch (SignatureException exception) {
            log.info("토큰 서명이 적절하지 않습니다.");
        }
        return false;
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                       .setSigningKey(secretKey)
                       .parseClaimsJws(accessToken)
                       .getBody();
        } catch (ExpiredJwtException exception) {
            return exception.getClaims();
        }
    }
}
