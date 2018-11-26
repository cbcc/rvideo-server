package cn.scau.rvideo.server.auth.service;

import cn.scau.rvideo.server.auth.AuthProperties;
import cn.scau.rvideo.server.auth.token.UserToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

/**
 * 提供创建和解析JWT服务
 *
 * @author cbc
 */
@Service
public class JwtService {
    @Resource
    private AuthProperties authProperties;
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createToken(UserToken userToken) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // 设置JWT
        JwtBuilder builder = Jwts.builder().setId(userToken.getId().toString())
                .setIssuedAt(now)
                .claim("id", userToken.getId())
                .claim("name", userToken.getName())
                .claim("email", userToken.getEmail())
                .claim("roles", userToken.getRoles())
                .signWith(key,signatureAlgorithm);
        // 设置过期时间
        long ttlMillis = authProperties.getToken().getExpiration().toMillis();
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return authProperties.getTokenPrefix() + builder.compact();
    }

    public UserToken parseToken(String token) throws JwtException {
        token = token.replace(authProperties.getTokenPrefix(), "");
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token).getBody();
        UserToken tokenUser = new UserToken();
        tokenUser.setId(Integer.parseInt(claims.getId()));
        tokenUser.setName(claims.get("name", String.class));
        tokenUser.setEmail(claims.get("email", String.class));
        tokenUser.setRoles((List<String>) claims.get("roles", List.class));
        return tokenUser;
    }
}
