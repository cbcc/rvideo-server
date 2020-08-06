package cn.scau.rvideo.server.service.impl;

import cn.scau.rvideo.server.service.UserService;
import cn.scau.rvideo.server.entity.User;
import cn.scau.rvideo.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User register(User user) {
        try {
            if (userMapper.save(user) == 1) {
                return user;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public User login(String email, String password) {
        try {
            return userMapper.findByEmailAndPassword(email, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public Integer delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public User getDetail(Integer id) {
        try {
            return userMapper.get(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isEmailExisted(String email) {
        User user = userMapper.findByEmail(email);
        return user != null;
    }

    @Override
    public Integer updateCustomFields(Integer id, String name, String sign, Integer sex) {
        try {
            return userMapper.updateCustomFields(id, name, sign, sex);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer updateFace(Integer id, String face) {
        try {
            return userMapper.updateFace(id, face);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<User> findLikeName(String name) {
        return userMapper.findLikeName(name);
    }

}
