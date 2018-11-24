package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("dddaaaa");
        user.setPassword("123456");
        user.setEmail("3@1");
        user.setSex("1");
        System.out.println(userMapper.save(user));
    }

    @Test
    public void findByEmailAndPsw(){
        User user =userMapper.findByEmailAndPsw("1@1","1");
        System.out.println(user);
    }

    @Test
    public void testChange() {
        userMapper.changeName(1, "aa");
        userMapper.changeSign(1, "haha");
    }
}
