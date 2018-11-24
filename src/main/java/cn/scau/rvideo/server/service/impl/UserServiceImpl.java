package cn.scau.rvideo.server.service.impl;

import cn.scau.rvideo.server.service.UserService;
import cn.scau.rvideo.server.entity.User;
import cn.scau.rvideo.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User register(User user) {
        try{
            if(userMapper.save(user) == 1) {
                return user;
            }
            return null;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public User login(String email, String password) {
        try{
            return userMapper.findByEmailAndPsw(email, password);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public User getDetail(Integer id) {
        try{
            return userMapper.get(id);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer updateCustomFields (Integer id, String name, String sign, Integer sex) {
        try{
            return userMapper.updateCustomFields(id, name, sign, sex);
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
