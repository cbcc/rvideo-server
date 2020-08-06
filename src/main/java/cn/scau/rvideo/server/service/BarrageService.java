package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.Barrage;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BarrageService {

    Integer save(Barrage barrage);

    @Nullable
    Barrage get(Integer id);

    Integer delete(Integer id);

    List<Barrage> findByVideoId(Integer videoId);
}
