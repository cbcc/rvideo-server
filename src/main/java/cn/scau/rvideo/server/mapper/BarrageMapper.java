package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Barrage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "BarrageMapper")
public interface BarrageMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into barrage(user_id, video_id, content) value(#{userId}, #{videoId}, #{content})")
    Integer save(Barrage barrage);

    @Select("select * from barrage where id = #{id}")
    Barrage get(Integer id);

    @Delete("delete from barrage where id = #{id}")
    Integer delete(Integer id);

    @Select("select * from barrage where video_id = #{videoId}")
    List<Barrage> findByVideoId(Integer videoId);
}
