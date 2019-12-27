package com.example.demo1.Controller;

import com.example.demo1.Mapper.QuestionMaper;
import com.example.demo1.Mapper.UserMapper;
import com.example.demo1.entity.QuestionEntity;
import com.example.demo1.model.Question;
import com.example.demo1.model.User;
import com.example.demo1.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 页面实现类
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue="1")Integer page,
                        @RequestParam(name="size",defaultValue="5")Integer size) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionEntity> questionList=questionService.list();
        model.addAttribute("questions",questionList);
        return "index";
    }
}
