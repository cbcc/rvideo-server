package cn.scau.rvideo.server.auth.service;

import cn.scau.rvideo.server.auth.token.UserToken;

public interface UserTokenService {
    UserToken loadByEmailAndPassword(String email, String password);
}
