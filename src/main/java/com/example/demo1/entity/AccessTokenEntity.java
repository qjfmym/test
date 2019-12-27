package com.example.demo1.entity;

import lombok.Data;

@Data
public class AccessTokenEntity {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
