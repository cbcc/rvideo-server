package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Video;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "VideoMapper")
public interface VideoMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into video(user_id,name,tag,face,file,introduction)" +
            "value (#{userId},#{name},#{tag},#{face},#{file},#{introduction})")
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

    @Update("update video set likes = #{likes} where id = #{id}")
    Integer updateLikes(@Param("id") Integer id, @Param("likes") Integer likes);

    @Update("update video set sign = #{views} where id = #{id}")
    Integer updateViews(@Param("id") Integer id, @Param("views") Integer views);
}
