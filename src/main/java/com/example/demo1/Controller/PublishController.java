package com.example.demo1.Controller;

import com.example.demo1.Mapper.QuestionMaper;
import com.example.demo1.model.Question;
import com.example.demo1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired//调用
    private QuestionMaper questionMaper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tag")String tag,
                            HttpServletRequest request,
                            Model model){
        //进行非空判断的校验
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title==null || title== ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        } if (description==null || description== ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        } if (tag==null || tag== ""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        User user =(User)request.getSession().getAttribute("user");
        //判断是否登录
        if (user==null){
            model.addAttribute("error","用户未登录");
            //不成功，刷新publish页面
            return "publish";
        }
        //进行数据的添加
        Question question=new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());//System.currentTimeMillis()是获取当前创建的时间
        question.setGmt_modified(question.getGmt_create());

        questionMaper.create(question);
        return "redirect:/";//如果成功了，提交问题，重定向回首页
    }
}
