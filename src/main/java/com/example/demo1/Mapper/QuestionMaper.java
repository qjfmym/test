package com.example.demo1.Mapper;

import com.example.demo1.entity.QuestionEntity;
import com.example.demo1.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMaper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question quetion);

    @Select("select * from question limit #{offset} ,#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offset} ,#{size}")
    List<Question> listByUserId( @Param("userId")Integer userId, @Param("offset")Integer offset,@Param("size") Integer size);

    @Select("select * from question where id=#{id}")
    Question getById(@Param("id") Integer id);
}
