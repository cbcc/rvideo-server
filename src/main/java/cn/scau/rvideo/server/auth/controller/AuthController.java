package cn.scau.rvideo.server.auth.controller;

import cn.scau.rvideo.server.auth.AuthStatus;
import cn.scau.rvideo.server.auth.service.JwtService;
import cn.scau.rvideo.server.auth.service.UserTokenService;
import cn.scau.rvideo.server.auth.token.UserToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AuthController {

    @Resource
    private UserTokenService userTokenService;
    @Resource
    private JwtService jwtService;

    @PostMapping("api/user/login")
    public AuthResponse login(@RequestBody @Validated LoginParam loginParam){
        UserToken userToken = userTokenService.loadByEmailAndPassword(loginParam.getEmail(), loginParam.getPassword());
        AuthResponse authResponse = new AuthResponse();
        if(userToken != null){
            authResponse.setToken(jwtService.createToken(userToken)).setStatus(AuthStatus.SUCCESS).setMessage("登录成功");
        } else {
            authResponse.setStatus(AuthStatus.FAIL).setMessage("账号或密码错误");
        }
        return authResponse;
    }
}
