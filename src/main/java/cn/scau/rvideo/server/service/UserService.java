package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.User;
import org.springframework.lang.Nullable;

public interface UserService {

    @Nullable
    User register(User user);

    @Nullable
    User login(String email, String password);

    @Nullable
    User getDetail(Integer id);

    Integer updateCustomFields(Integer id, String name, String sign, Integer sex);

    Integer updateFace(Integer id, String face);
}
