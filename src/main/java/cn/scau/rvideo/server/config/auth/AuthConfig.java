package cn.scau.rvideo.server.config.auth;

import cn.scau.rvideo.server.auth.AuthConfigurer;
import cn.scau.rvideo.server.auth.service.UserTokenService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig extends AuthConfigurer {

    @Override
    public UserTokenService userTokenService() {
        return new TokenService();
    }
}
