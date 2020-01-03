package com.example.demo1.service;

import com.example.demo1.Mapper.UserMapper;
import com.example.demo1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser =userMapper.findByAccountId(user.getAccount_Id());
        if (dbuser==null){
            //插入
            user.setGmt_Create(System.currentTimeMillis());
            user.setGmt_Modified(user.getGmt_Create());
            userMapper.insert(user);//插入到Mapper里
        }else{
            //更新
            dbuser.setGmt_Modified(System.currentTimeMillis());
            dbuser.setAvatar_Url(user.getAvatar_Url());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.update(dbuser);
        }
    }
}
