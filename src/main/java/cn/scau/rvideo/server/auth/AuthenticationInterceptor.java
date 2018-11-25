package cn.scau.rvideo.server.auth;

import cn.scau.rvideo.server.auth.service.JwtService;
import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.auth.token.UserToken;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private AuthProperties authProperties;
    @Resource
    private JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(Token.class)) {
            Token tokenAnnotation = method.getAnnotation(Token.class);
            if (tokenAnnotation.required()) {
                final String token = request.getHeader(authProperties.getHeaderName());
                UserToken userToken;
                // 登录认证
                if (token != null && !"null".equals(token)) {
                    try {
                        userToken = jwtService.parseToken(token);
                    } catch (ExpiredJwtException expiredJwtException) {
                        logger.debug("{}",expiredJwtException);
                        response.setStatus(AuthStatus.AUTH_EXPIRED);
                        return false;
                    } catch (JwtException jwtException) {
                        logger.debug("{}", jwtException);
                        response.setStatus(AuthStatus.AUTH_ILLEGAL);
                        return false;
                    }
                } else {
                    response.setStatus(AuthStatus.AUTH_REQUIRED);
                    return false;
                }
                // 权限认证
                if (userToken != null) {
                    return verifyRoles(tokenAnnotation.roles(), userToken.getRoles(), response);
                }
            }
        }
        return true;
    }

    private boolean verifyRoles(String[] roles, List<String> userRoles, HttpServletResponse response) {
        for (String role: roles) {
            boolean hasRole = false;
            for (String userRole: userRoles) {
                if (role.equals(userRole)) {
                    hasRole = true;
                    break;
                }
            }
            if (!hasRole) {
                response.setStatus(AuthStatus.AUTH_UNAUTHORIZED);
                return false;
            }
        }
        return true;
    }
}
