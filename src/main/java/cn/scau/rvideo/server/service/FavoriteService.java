package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.Favorite;
import org.springframework.lang.Nullable;

import java.util.List;

public interface FavoriteService {

    Integer save(Favorite favorite);

    @Nullable
    Favorite get(Integer userId, Integer videoId);

    Integer delete(Integer userId, Integer videoId);

    List<Integer> findVideoIdByUserId(Integer userId);
}
