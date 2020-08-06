package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testRegister() {
        User user = new User();
        user.setEmail("2@2");
        user.setName("cat");
        user.setPassword("123456");
        user.setSex(1);
        user.setRoles("USER");
        user = userService.register(user);
        assert user != null;
        System.out.println(user);
    }
}
