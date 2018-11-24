package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.entity.Video;

import java.util.List;

public interface VideoService {

    Integer save(Video video);
    Integer delete(Integer id);
    Video get(Integer id);
    List<Video> findByUserId(Integer userId);
    List<Video> findLikeName(String name);
    List<Video> findByTag(String tag);

    Integer changeLikes(Integer id, Integer likes);
    Integer changeViews(Integer id, Integer views);

/*    VideoInfo findVideoInfoById(Integer id);
    List<VideoInfo> findVideoInfoLikeName(String name);
    List<VideoInfo> findVideoInfoByTag(String tag);
    List<VideoInfo> findVideoInfoByTagLimit(String tag,Integer count);*/
}
