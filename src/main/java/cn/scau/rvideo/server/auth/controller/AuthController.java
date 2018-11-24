package cn.scau.rvideo.server.auth.controller;

import cn.scau.rvideo.server.auth.service.JwtService;
import cn.scau.rvideo.server.auth.service.UserTokenService;
import cn.scau.rvideo.server.auth.token.UserToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Resource
    private UserTokenService userTokenService;
    @Resource
    private JwtService jwtService;

    @PostMapping("api/user/login")
    public AuthResponse login(@RequestBody LoginParam loginParam, HttpServletResponse res){
        UserToken userToken = userTokenService.loadByEmailAndPassword(loginParam.getEmail(), loginParam.getPassword());
        AuthResponse authResponse = new AuthResponse();
        if(userToken != null){
            authResponse.setStatus(1).setMessage("登录成功");
            // 带上JWT
            jwtService.addToken(userToken, res);
        } else {
            authResponse.setStatus(-1).setMessage("账号或密码错误");
        }
        return authResponse;
    }
}
