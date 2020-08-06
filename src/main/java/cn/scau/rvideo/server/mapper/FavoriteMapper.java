package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Favorite;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "FavoriteMapper")
public interface FavoriteMapper {

    @Insert("insert into favorite(user_id, video_id) value(#{userId}, #{videoId})")
    Integer save(Favorite favorite);

    @Select("select user_id, video_id from favorite " +
            "where user_id = #{userId} and video_id = #{videoId}")
    Favorite get(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    @Delete("delete from favorite where user_id = #{userId} and video_id = #{videoId}")
    Integer delete(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    @Select("select video_id from favorite where user_id = #{userId}")
    List<Integer> findVideoIdByUserId(Integer userId);
}
