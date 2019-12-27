package com.example.demo1.entity;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private  long id;
    private String bio;
    private String avatar_url;

}
