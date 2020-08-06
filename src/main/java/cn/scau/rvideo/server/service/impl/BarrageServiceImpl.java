package cn.scau.rvideo.server.service.impl;

import cn.scau.rvideo.server.entity.Barrage;
import cn.scau.rvideo.server.mapper.BarrageMapper;
import cn.scau.rvideo.server.service.BarrageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BarrageServiceImpl implements BarrageService {

    @Resource
    private BarrageMapper barrageMapper;

    @Override
    public Integer save(Barrage barrage) {
        return barrageMapper.save(barrage);
    }

    @Override
    public Barrage get(Integer id) {
        return barrageMapper.get(id);
    }

    @Override
    public Integer delete(Integer id) {
        return barrageMapper.delete(id);
    }

    @Override
    public List<Barrage> findByVideoId(Integer videoId) {
        return barrageMapper.findByVideoId(videoId);
    }
}
