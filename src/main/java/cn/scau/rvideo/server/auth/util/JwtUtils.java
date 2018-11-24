package cn.scau.rvideo.server.auth.util;

import cn.scau.rvideo.server.auth.token.UserToken;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

public class JwtUtils {

    public static String createJWT(UserToken tokenUser, long ttlMillis, SecretKey key) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(tokenUser.getId().toString())
                .setIssuedAt(now)
                .claim("name", tokenUser.getName())
                .claim("email", tokenUser.getEmail())
                .claim("roles", tokenUser.getRoles())
                .signWith(key,signatureAlgorithm);

        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static UserToken parseJWT(String jwt, SecretKey key) throws JwtException {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        UserToken tokenUser = new UserToken();
        tokenUser.setId(Integer.parseInt(claims.getId()));
        tokenUser.setName(claims.get("name", String.class));
        tokenUser.setEmail(claims.get("email", String.class));
        tokenUser.setRoles((List<String>) claims.get("roles", List.class));
        return tokenUser;
    }
}
