package cn.scau.rvideo.server.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestListUtils {

    @Test
    public void testFromString() {
        String s = ";user,|admin";
        System.out.println(ListUtils.fromString(s));
    }
}
