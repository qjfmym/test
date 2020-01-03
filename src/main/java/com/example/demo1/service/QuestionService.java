package com.example.demo1.service;

import com.example.demo1.Mapper.QuestionMaper;
import com.example.demo1.Mapper.UserMapper;
import com.example.demo1.advice.CustomizeExceptionHandler;
import com.example.demo1.entity.PageUilt;
import com.example.demo1.entity.QuestionEntity;
import com.example.demo1.exception.CusomizeException;
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

    public PageUilt list(Integer page, Integer size) {
        Integer offset =size * (page -1);//获取到当前页码
        List<Question> questions=questionMaper.list(offset,size);
        List<QuestionEntity> questionEntityList=new ArrayList<>();

        PageUilt pageUilt =new PageUilt();//导入工具类
        for (Question question :questions){
            User user=userMapper.findById(question.getCreator());
            QuestionEntity questionEntity=new QuestionEntity();
            BeanUtils.copyProperties(question,questionEntity);//BeanUtils就是快速的把上面所有的属性拷贝到Entity里
            questionEntity.setUser(user);
            questionEntityList.add(questionEntity);//直接把数据放到数组里面
        }
        pageUilt.setQuestions(questionEntityList);//把在question查询到的数量放到page工具类里
        Integer totalCount =questionMaper.count();//获取总数
        pageUilt.setPage(totalCount,page,size);
        return pageUilt;
    }

    public PageUilt listByUserId(Integer userId, Integer page, Integer size) {
        Integer offset =size * (page -1);//获取到当前页码
        List<Question> questions=questionMaper.listByUserId(userId,offset,size);
        List<QuestionEntity> questionEntityList=new ArrayList<>();

        PageUilt pageUilt =new PageUilt();//导入工具类
        for (Question question :questions){
            User user=userMapper.findById(question.getCreator());
            QuestionEntity questionEntity=new QuestionEntity();
            BeanUtils.copyProperties(question,questionEntity);//BeanUtils就是快速的把上面所有的属性拷贝到Entity里
            questionEntity.setUser(user);
            questionEntityList.add(questionEntity);//直接把数据放到数组里面
        }
        pageUilt.setQuestions(questionEntityList);//把在question查询到的数量放到page工具类里
        Integer totalCount =questionMaper.count();//获取总数
        pageUilt.setPage(totalCount,page,size);
        return pageUilt;
    }

    public QuestionEntity getById(Integer id) {
     Question question=questionMaper.getById(id);
     if (question==null){
         throw new CusomizeException("你找的问题不在了，要不要换一个试试？");
     }
     QuestionEntity questionEntity=new QuestionEntity();
     BeanUtils.copyProperties(question,questionEntity);
     User user=userMapper.findById(question.getCreator());
     questionEntity.setUser(user);
     return  questionEntity;
    }
}
