package com.neusoft.ssm.controller;

import com.neusoft.ssm.bean.User;
import com.neusoft.ssm.service.LoginService;
import com.neusoft.ssm.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("loginController")
public class LoginController {
    @Resource
    LoginService loginService;

    /*
     * @Description 第一次跳转到主界面
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("first")
    public ModelAndView first() {
        return new ModelAndView("login");
    }

    /*
     * @Description 跳转到error界面
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("loginFailure")
    public ModelAndView loginFailure() {
        return new ModelAndView("loginFailure");
    }

    /*
     * @Description 登录验证密码后，将jwt的token放入header//TODO
     * @Param [httpServletResponse, loginName, password]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @RequestMapping("login")
    public Map<String,String> login(HttpServletResponse httpServletResponse, @RequestParam( "loginName") String loginName, @RequestParam("password") String password) {
        User user=new User();
        user.setLoginName(loginName);
        user.setPassword(password);
        User loginUser=loginService.checkLogin(user);
        //先到数据库验证
        if(-1 != loginUser.getId()) {
            //给用户jwt加密生成token
            String token = JwtUtil.sign(loginUser, 60L* 1000L* 30L);
            //封装成对象返回给客户端
            httpServletResponse.setHeader("Authorization",token);
        }
        Map<String, String> map = new HashMap<>();
        map.put("userId", loginUser.getId().toString());
        map.put("userName", loginUser.getTrustName());
        map.put("userCategory",loginUser.getUserCategory());//用户类别（1：挂号收费员，2：门诊医生，3：医技医生，4：药房操作员，5：财务管理员，6：医院管理员）
        return map;
    }

    /*
     * @Description 登录成功后，可以进行权限管理//TODO
     * @Param [httpServletResponse, loginName, password]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @RequestMapping("loginSuccess")
    public ModelAndView loginSuccess(@RequestParam( "userId") String userId, @RequestParam("userName") String userName,@RequestParam("userCategory") char userCategory) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("userName", userName);
        if (userCategory == '1')//todo
            modelAndView.setViewName("outpatientDoctorWorkstation");
        else modelAndView.setViewName("index");
        return modelAndView;
    }
}
