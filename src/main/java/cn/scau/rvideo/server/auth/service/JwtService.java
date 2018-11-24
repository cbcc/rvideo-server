package cn.scau.rvideo.server.auth.service;

import cn.scau.rvideo.server.auth.AuthProperties;
import cn.scau.rvideo.server.auth.token.UserToken;
import cn.scau.rvideo.server.auth.util.JwtUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class JwtService {
    @Resource
    private AuthProperties authProperties;
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public void addToken(UserToken userToken, HttpServletResponse res) {
        final String JWT = JwtUtils.createJWT(userToken, authProperties.getToken().getExpiration().toMillis(), key);
        // 跨域暴露响应头字段
        res.setHeader("Access-Control-Expose-Headers", authProperties.getHeaderName());
        res.setHeader(authProperties.getHeaderName(), authProperties.getTokenPrefix() + JWT);
    }

    public UserToken verifyToken(HttpServletRequest request) throws JwtException {
        final String token = request.getHeader(authProperties.getHeaderName());
        if (token != null && !"null".equals(token)){
            return JwtUtils.parseJWT(token.replace(authProperties.getTokenPrefix(), ""), key);
        }
        return null;
    }
}
