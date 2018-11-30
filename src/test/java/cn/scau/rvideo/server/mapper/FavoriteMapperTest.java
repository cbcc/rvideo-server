package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Favorite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteMapperTest {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Test
    public void testSave() {
        Favorite favorite = new Favorite();
        favorite.setUserId(1);
        favorite.setVideoId(1);
        assert favoriteMapper.save(favorite) > 0;
    }

    @Test
    public void testGet() {
        Favorite favorite = new Favorite();
        favorite.setUserId(1);
        favorite.setVideoId(1);
        favorite = favoriteMapper.get(favorite);
        assert favorite != null;
        System.out.println(favorite);
    }

    @Test
    public void testFindVideoIdByUserId() {
        List<Integer> list = favoriteMapper.findVideoIdByUserId(1);
        System.out.println(list);
    }

    @Test
    public void testDelete() {
        Favorite favorite = new Favorite();
        favorite.setUserId(1);
        favorite.setVideoId(1);
        assert favoriteMapper.delete(favorite) > 0;
    }
}
