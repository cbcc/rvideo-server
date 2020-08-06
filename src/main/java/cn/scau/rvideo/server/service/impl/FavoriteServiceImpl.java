package cn.scau.rvideo.server.service.impl;

import cn.scau.rvideo.server.entity.Favorite;
import cn.scau.rvideo.server.mapper.FavoriteMapper;
import cn.scau.rvideo.server.service.FavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteMapper favoriteMapper;

    @Override
    public Integer save(Favorite favorite) {
        return favoriteMapper.save(favorite);
    }

    @Override
    public Favorite get(Integer userId, Integer videoId) {
        return favoriteMapper.get(userId, videoId);
    }

    @Override
    public Integer delete(Integer userId, Integer videoId) {
        return favoriteMapper.delete(userId, videoId);
    }

    @Override
    public List<Integer> findVideoIdByUserId(Integer userId) {
        return favoriteMapper.findVideoIdByUserId(userId);
    }
}
