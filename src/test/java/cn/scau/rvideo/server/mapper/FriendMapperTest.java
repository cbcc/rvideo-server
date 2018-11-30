package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendMapperTest {

    @Resource
    private FriendMapper friendMapper;

    @Test
    public void testSave() {
        Friend friend = new Friend();
        friend.setUserId(1);
        friend.setFriendId(2);
        assert friendMapper.save(friend) > 0;
    }

    @Test
    public void testGet() {
        Friend friend = new Friend();
        friend.setUserId(1);
        friend.setFriendId(2);
        friend = friendMapper.get(friend);
        assert friend != null;
        System.out.println(friend);
    }

    @Test
    public void testFindFriendIdByUserId() {
        List<Integer> list = friendMapper.findFriendIdByUserId(1);
        System.out.println(list);
    }

    @Test
    public void testDelete() {
        Friend friend = new Friend();
        friend.setUserId(1);
        friend.setFriendId(2);
        assert friendMapper.delete(friend) > 0;
    }
}
