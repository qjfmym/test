package com.example.demo1.Controller;

import com.example.demo1.Mapper.UserMapper;
import com.example.demo1.entity.AccessTokenEntity;
import com.example.demo1.entity.GithubUser;
import com.example.demo1.model.User;
import com.example.demo1.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 用于解析GitHou的账号地址授权后，返回到自己的页面
 */
@Controller
public class AuthorizeController {
    /**自動加载实例到上下文
     */
    @Autowired
    private GithubProvider githubProvider;
    /**
     * value(${})是读取application.properties里的配置文件，方便在不同的环境时直接在applictiont里修改
     */
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clientSercret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletResponse response
                           )
    {
        AccessTokenEntity accessTokenEntity=new AccessTokenEntity();
        //设置参数
        accessTokenEntity.setCode(code);
        accessTokenEntity.setClient_id(clientid);
        accessTokenEntity.setClient_secret(clientSercret);
        accessTokenEntity.setRedirect_uri(redirectUri);
        accessTokenEntity.setState(state);
//        //获取实体类的参数
        String accessToken=githubProvider.getAccessToken(accessTokenEntity);
//        //获取user
        GithubUser githubUser =githubProvider.getUser(accessToken);
        //System.out.println(githubUser.getName());
        if (githubUser!=null){
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccount_Id(String.valueOf(githubUser.getId()));//String .valueOF()是强制转换
            user.setGmt_Create(System.currentTimeMillis());
            user.setGmt_Modified(user.getGmt_Create());
            user.setAvatar_Url(githubUser.getAvatar_url());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            //登录成功，写session和cookie（登录状态和登录持续时间）
            //request.getSession().setAttribute("user",user);
            return "redirect:/";//重定向到index页面
        }else{
            //登录失败，重新登录
            return "redirect:/";//重定向到index页面
        }
    }
}
