package com.example.demo1.Controller;

import com.example.demo1.entity.AccessTokenEntity;
import com.example.demo1.entity.GithubUser;
import com.example.demo1.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Value("${github.clinent.id}")
    private String clnintid;
    @Value("${github.client.secret}")
    private String clientSercret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){
        AccessTokenEntity accessTokenEntity=new AccessTokenEntity();
        //设置参数
        accessTokenEntity.setCode(code);
        accessTokenEntity.setClinent_id(clnintid);
        accessTokenEntity.setClient_secret(clientSercret);
        accessTokenEntity.setRedirect_uri(redirectUri);
        accessTokenEntity.setState(state);
//        //获取实体类的参数
        String accessToken=githubProvider.getAccessToken(new AccessTokenEntity());
//        //获取user
        GithubUser user =githubProvider.gitUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
