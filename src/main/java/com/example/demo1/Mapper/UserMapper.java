package com.example.demo1.Mapper;

import com.example.demo1.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("Insert into user(name,account_Id,token,gmt_Create,gmt_Modified) value(#{name},#{account_Id},#{token},#{gmt_Create},#{gmt_Modified})")
    void insert(User user);
}
