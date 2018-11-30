package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Barrage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarrageMapperTest {

    @Resource
    private BarrageMapper barrageMapper;

    @Test
    public void testSave() {
        Barrage barrage = new Barrage();
        barrage.setUserId(1);
        barrage.setVideoId(1);
        barrage.setContent("haha");
        assert barrageMapper.save(barrage) > 0;
        System.out.println(barrage);
    }

    @Test
    public void testGet() {
        Barrage barrage = barrageMapper.get(1);
        assert barrage != null;
        System.out.println(barrage);
    }

    @Test
    public void testFindByVideoId() {
        List<Barrage> list = barrageMapper.findByVideoId(1);
        System.out.println(list);
    }

    @Test
    public void testDelete() {
        assert barrageMapper.delete(1) > 0;
    }
}
