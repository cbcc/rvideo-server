package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.Video;
import org.springframework.lang.Nullable;

import java.util.List;

public interface VideoService {

    @Nullable
    Video save(Video video);

    Integer delete(Integer id);

    @Nullable
    Video get(Integer id);

    List<Video> getAll();

    List<Video> getIn(List<Integer> idList);

    List<Video> findByUserId(Integer userId);

    List<Video> findLikeName(String name);

    List<Video> findByTag(String tag);

    List<Video> findByVerify(Integer verify);

    Integer updateLikes(Integer id, Integer likes);

    Integer updateViews(Integer id, Integer views);

    Integer updateVerify(Integer id, Integer verify);
}
