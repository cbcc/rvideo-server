package cn.scau.rvideo.server.auth;

import cn.scau.rvideo.server.auth.service.UserTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

public abstract class AuthConfigurer implements WebMvcConfigurer {

    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Bean
    public abstract UserTokenService userTokenService();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");
    }
}
