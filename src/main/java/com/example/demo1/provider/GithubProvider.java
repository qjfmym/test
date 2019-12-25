package com.example.demo1.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.entity.AccessTokenEntity;
import com.example.demo1.entity.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用于解析github，实现git请求
 * @Component加载上下文
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenEntity accessTokenEntity){
        MediaType mediaType= MediaType.get("application/json;charset=utf-8");
        OkHttpClient client =new OkHttpClient();

            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenEntity));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/authorize")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                   String string =response.body().string();
                   System.out.println(string);
                   String token =string.split("&")[0].split("=")[1];
                   return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }
    public GithubUser gitUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request =new Request.Builder().url("https://api.github.com/user?access_token="+accessToken).build();
        try {
            Response response=client.newCall(request).execute();
            String string =response.body().string();
            //System.out.println(string);
            GithubUser githubUser= JSON.parseObject(string,GithubUser.class);
           // System.out.println(githubUser.toString());
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
