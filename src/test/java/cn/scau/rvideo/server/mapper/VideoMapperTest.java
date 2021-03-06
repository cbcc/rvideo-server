package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoMapperTest {

    @Resource
    private VideoMapper videoMapper;

    @Test
    public void testSave() {
        Video video = new Video();
        video.setName("DiabloIII");
        video.setIntroduction("game video");
        video.setTag("game");
        video.setUserId(1);
        assert videoMapper.save(video) == 1;
    }

    @Test
    public void testGet() {
        Video video = videoMapper.get(1);
        System.out.println(video);
    }

    @Test
    public void testGetIn() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        List<Video> list = videoMapper.getIn(idList);
        System.out.println(list);
    }
    @Test
    public void testDelete() {
        assert videoMapper.delete(1) == 1;
    }
}
