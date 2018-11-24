package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Video;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "VideoMapper")
public interface VideoMapper {

    @Insert("insert into video(user_id,name,tag,thumbnail,file,introduction)" +
            "value (#{userId},#{name},#{tag},#{thumbnail},#{file},#{introduction})")
    Integer save(Video video);

    @Delete("delete from video where id = #{id}")
    Integer delete(Integer id);

    @Select("select * from video where id = #{id}")
    Video get(Integer id);

    @Select("select * from video where user_id = #{userId}")
    List<Video> findByUserId(Integer userId);

    @Select("select * from video where name like CONCAT('%',#{name},'%')")
    List<Video> findLikeName(String name);

    @Select("select * from video where tag = #{tag}")
    List<Video> findByTag(String tag);

    Integer changeLikes(@Param("id") Integer id, @Param("likes") Integer likes);
    Integer changeViews(@Param("id") Integer id, @Param("views") Integer views);

/*    VideoInfo findVideoInfoById(Integer id);
    List<VideoInfo> findVideoInfoLikeName(String name);
    List<VideoInfo> findVideoInfoByTag(String tag);
    List<VideoInfo> findVideoInfoByTagLimit(@Param("tag") String tag, @Param("count") Integer count);*/

}
