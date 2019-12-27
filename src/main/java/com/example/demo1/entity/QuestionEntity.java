package com.example.demo1.entity;

import com.example.demo1.model.User;
import lombok.Data;

@Data
public class QuestionEntity {
    private Integer id;
    private String title;
    private String  description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer view_Count;
    private Integer comment_Count;
    private Integer like_Count;
    private User user;
}
