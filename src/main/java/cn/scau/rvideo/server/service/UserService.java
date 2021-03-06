package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService {

    @Nullable
    User register(User user);

    @Nullable
    User login(String email, String password);

    List<User> getAll();

    Integer delete(Integer id);

    @Nullable
    User getDetail(Integer id);

    boolean isEmailExisted(String email);

    Integer updateCustomFields(Integer id, String name, String sign, Integer sex);

    Integer updateFace(Integer id, String face);

    List<User> findLikeName(String name);
}
