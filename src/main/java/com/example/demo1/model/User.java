package com.example.demo1.model;

import lombok.Data;

@Data
public class User {
    private  Integer id;
    private  String name;
    private  String account_Id;
    private  String token;
    private  Long gmt_Create;
    private  Long gmt_Modified;
    private  String avatar_Url;

}
