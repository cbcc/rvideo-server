package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.User;

public interface UserService {

    User register(User user);
    User login(String email, String password);
    User getDetail(Integer id);
    Integer updateCustomFields (Integer id, String name, String sign, Integer sex);
}
