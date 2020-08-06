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

    @Select("select * from video")
    List<Video> getAll();

    @Select("<script>" +
            "select * from video where verify = 1 and id in" +
            "<foreach item='id' index='index' collection='idList' open='(' separator=',' close=')'> #{id} </foreach>" +
            "</script>")
    List<Video> getIn(@Param("idList") List<Integer> idList);

    @Select("select * from video where user_id = #{userId} and verify = 1")
    List<Video> findByUserId(Integer userId);

    @Select("select * from video where name like CONCAT('%',#{name},'%')  and verify = 1")
    List<Video> findLikeName(String name);

    @Select("select * from video where tag = #{tag} and verify = 1")
    List<Video> findByTag(String tag);

    @Select("select * from video where verify = #{verify}")
    List<Video> findByVerify(Integer verify);

    @Update("update video set likes = #{likes} where id = #{id}")
    Integer updateLikes(@Param("id") Integer id, @Param("likes") Integer likes);

    @Update("update video set views = #{views} where id = #{id}")
    Integer updateViews(@Param("id") Integer id, @Param("views") Integer views);

    @Update("update video set verify = #{verify} where id = #{id}")
    Integer updateVerify(@Param("id") Integer id, @Param("verify") Integer verify);
}
