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
import java.io.IOException;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        response.setHeader("Access-Control-Allow-Headers", "content-type");
        response.setHeader("Access-Control-Allow-Origin","*");
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
                        logger.debug("凭证已过期: {}",token);
                        response.sendError(AuthStatus.AUTH_EXPIRED, "过期凭证");
                        return false;
                    } catch (JwtException jwtException) {
                        logger.debug("不信任凭证: {}", token);
                        response.sendError(AuthStatus.AUTH_ILLEGAL, "不信任凭证");
                        return false;
                    }
                } else {
                    logger.debug("需要认证");
                    response.sendError(AuthStatus.AUTH_REQUIRED, "需要认证");
                    return false;
                }
                // 权限认证
                if (userToken != null) {
                    if(!hasAnyRole(tokenAnnotation.roles(), userToken.getRoles())) {
                        logger.debug("没有权限: {}", userToken);
                        response.sendError(AuthStatus.AUTH_UNAUTHORIZED, "没有权限");
                    }
                }
            }
        }
        return true;
    }

    private boolean hasAnyRole(String[] roles, List<String> userRoles) {
        for (String role: roles) {
            for (String userRole: userRoles) {
                if (role.equals(userRole)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasAllRoles(String[] roles, List<String> userRoles) {
        for (String role: roles) {
            boolean hasRole = false;
            for (String userRole: userRoles) {
                if (role.equals(userRole)) {
                    hasRole = true;
                    break;
                }
            }
            if (!hasRole) {
                return false;
            }
        }
        return true;
    }
}
