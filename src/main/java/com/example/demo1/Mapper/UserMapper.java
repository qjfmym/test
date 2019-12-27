package com.example.demo1.Mapper;

import com.example.demo1.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("Insert into user(name,account_Id,token,gmt_Create,gmt_Modified,avatar_Url) value(#{name},#{account_Id},#{token},#{gmt_Create},#{gmt_Modified},#{avatar_Url})")
    void insert(User user);

    @Select("Select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("Select * from user where id = #{id}")
    User findById(@Param("id")Integer id);
}
