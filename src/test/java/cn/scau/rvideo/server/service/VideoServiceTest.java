package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoServiceTest {

    @Resource
    private VideoService videoService;

    @Test
    public void testSave() {
        Video video = new Video();
        video.setName("DiabloII");
        video.setIntroduction("game video");
        video.setTag("game");
        video.setUserId(1);
        video = videoService.save(video);
        assert video != null;
        System.out.println(video);
    }

    @Test
    public void testFindLikeName() {
        List<Video> list = videoService.findLikeName("dd");
        System.out.println(list);
    }
}
