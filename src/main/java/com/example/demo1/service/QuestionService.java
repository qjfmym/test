package com.example.demo1.service;

import com.example.demo1.Mapper.QuestionMaper;
import com.example.demo1.Mapper.UserMapper;
import com.example.demo1.entity.QuestionEntity;
import com.example.demo1.model.Question;
import com.example.demo1.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMaper questionMaper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionEntity> list() {
        List<Question> questions=questionMaper.list();
        List<QuestionEntity> questionEntityList=new ArrayList<>();
        for (Question question :questions){
            User user=userMapper.findById(question.getCreator());
            QuestionEntity questionEntity=new QuestionEntity();
            BeanUtils.copyProperties(question,questionEntity);//BeanUtils就是快速的把上面所有的属性拷贝到Entity里
            questionEntity.setUser(user);
            questionEntityList.add(questionEntity);//直接把数据放到数组里面
        }
        return questionEntityList;
    }
}
