package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "UserMapper")
public interface UserMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into user(name,password,email,sex) value(#{name},#{password},#{email},#{sex})")
    Integer save(User user);

    @Select("select * from user where id = #{id}")
    User get(Integer id);

    @Select("select * from user")
    List<User> getAll();

    Integer update(User user);

    @Update("update user set name = #{name}, sign = #{sign}, sex = #{sex} where id = #{id}")
    Integer updateCustomFields (@Param("id") Integer id, @Param("name") String name,
                                @Param("sign") String sign, @Param("sex") Integer sex);

    @Select("select * from user " +
            "where email = #{email} and password = #{password}")
    User findByEmailAndPsw(@Param("email") String email, @Param("password") String password);

    @Select("select * from user" +
            "where nickname like CONCAT('%',#{nickname},'%'")
    List<User> findLikeNickname(String nickname);

    User findByEmail(@Param("email") String email);

    @Update("update user set name = #{name} where id = #{id}")
    Integer changeName(@Param("id") Integer id, @Param("name") String name);
    @Update("update user set sign = #{sign} where id = #{id}")
    Integer changeSign(@Param("id") Integer id, @Param("sign") String sign);

    Integer changePassword(@Param("id") Integer id, @Param("password") String password);
    Integer changeHead(@Param("id") Integer id, @Param("head") String head);

    
}
