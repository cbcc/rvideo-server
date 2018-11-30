package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.Friend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "FriendMapper")
public interface FriendMapper {

    @Insert("insert into friend(user_id, friend_id) value(#{userId}, #{friendId})")
    Integer save(Friend friend);

    @Select("select user_id, friend_id from friend " +
            "where user_id = #{userId} and friend_id = #{friendId}")
    Friend get(Friend friend);

    @Delete("delete from friend where user_id = #{userId} and friend_id = #{friendId}")
    Integer delete(Friend friend);

    @Select("select friend_id from friend where user_id = #{userId}")
    List<Integer> findFriendIdByUserId(Integer userId);
}
