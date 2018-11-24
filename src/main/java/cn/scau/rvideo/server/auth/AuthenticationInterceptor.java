package cn.scau.rvideo.server.auth;

import cn.scau.rvideo.server.auth.service.JwtService;
import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.auth.entity.UserToken;
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

    @Resource(name = "jwtService")
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
            Token token = method.getAnnotation(Token.class);
            if (token.required()) {
                try {
                    // 登录认证
                    UserToken userToken = jwtService.verifyToken(request);
                    logger.debug("{}", userToken);
                    if (userToken == null) {
                        return false;
                    }
                    // 权限认证
                    return verifyRoles(token.roles(), userToken.getRoles());
                } catch (JwtException e) {
                    logger.info("JwtException");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean verifyRoles(String[] roles, List<String> userRoles) {
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
