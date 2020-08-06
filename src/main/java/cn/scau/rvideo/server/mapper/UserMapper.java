package cn.scau.rvideo.server.mapper;

import cn.scau.rvideo.server.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "UserMapper")
public interface UserMapper {

    @Options(useGeneratedKeys = true)
    @Insert("insert into user(name, password, email, sex, roles) " +
            "value(#{name}, #{password}, #{email}, #{sex}, #{roles})")
    Integer save(User user);

    @Select("select * from user where id = #{id}")
    User get(Integer id);

    @Select("select * from user")
    List<User> getAll();

    @Delete("delete from user where id = #{id}")
    Integer delete(Integer id);

    Integer update(User user);

    @Update("update user set name = #{name}, sign = #{sign}, sex = #{sex} where id = #{id}")
    Integer updateCustomFields(@Param("id") Integer id, @Param("name") String name,
                               @Param("sign") String sign, @Param("sex") Integer sex);

    @Select("select * from user " +
            "where email = #{email} and password = #{password}")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Select("select * from user " +
            "where name like CONCAT('%',#{name},'%')")
    List<User> findLikeName(String name);

    @Select("select * from user where email = #{email}")
    User findByEmail(@Param("email") String email);

    @Update("update user set name = #{name} where id = #{id}")
    Integer updateName(@Param("id") Integer id, @Param("name") String name);

    @Update("update user set face = #{face} where id = #{id}")
    Integer updateFace(@Param("id") Integer id, @Param("face") String face);

    @Update("update user set sign = #{sign} where id = #{id}")
    Integer updateSign(@Param("id") Integer id, @Param("sign") String sign);

    Integer updatePassword(@Param("id") Integer id, @Param("password") String password);
}
