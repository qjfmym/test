package com.example.demo1.Controller;

import com.example.demo1.entity.QuestionEntity;
import com.example.demo1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Integer id,
                           Model model){
        QuestionEntity questionEntity=questionService.getById(id);//获取id，通过id进行数据获取
        model.addAttribute("question",questionEntity);
        return "question";
    }
}
