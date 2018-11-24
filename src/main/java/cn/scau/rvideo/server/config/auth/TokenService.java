package cn.scau.rvideo.server.config.auth;

import cn.scau.rvideo.server.auth.service.UserTokenService;
import cn.scau.rvideo.server.auth.token.UserToken;
import cn.scau.rvideo.server.entity.User;
import cn.scau.rvideo.server.service.UserService;
import cn.scau.rvideo.server.util.ListUtils;

import javax.annotation.Resource;

public class TokenService implements UserTokenService {

    @Resource
    private UserService userService;

    @Override
    public UserToken loadByEmailAndPassword(String email, String password) {
        User user = userService.login(email, password);
        if (user != null) {
            UserToken userToken = new UserToken();
            userToken.setId(user.getId());
            userToken.setName(user.getName());
            userToken.setEmail(user.getEmail());
            userToken.setRoles(ListUtils.fromString(user.getRoles()));
            return userToken;
        }
        return null;
    }
}
