package cn.scau.rvideo.server.service.impl;

import cn.scau.rvideo.server.entity.Video;
import cn.scau.rvideo.server.mapper.VideoMapper;
import cn.scau.rvideo.server.service.VideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Override
    public Video save(Video video) {
        if (videoMapper.save(video) == 1) {
            return video;
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return videoMapper.delete(id);
    }

    @Override
    public Video get(Integer id) {
        return videoMapper.get(id);
    }

    @Override
    public List<Video> getAll() {
        return videoMapper.getAll();
    }

    @Override
    public List<Video> getIn(List<Integer> idList) {
        return videoMapper.getIn(idList);
    }

    @Override
    public List<Video> findByUserId(Integer userId) {
        return videoMapper.findByUserId(userId);
    }

    @Override
    public List<Video> findLikeName(String name) {
        return videoMapper.findLikeName(name);
    }

    @Override
    public List<Video> findByTag(String tag) {
        return videoMapper.findByTag(tag);
    }

    @Override
    public List<Video> findByVerify(Integer verify) {
        return videoMapper.findByVerify(verify);
    }

    @Override
    public Integer updateLikes(Integer id, Integer likes) {
        return videoMapper.updateLikes(id, likes);
    }

    @Override
    public Integer updateViews(Integer id, Integer views) {
        return videoMapper.updateViews(id, views);
    }

    @Override
    public Integer updateVerify(Integer id, Integer verify) {
        return videoMapper.updateVerify(id, verify);
    }
}
