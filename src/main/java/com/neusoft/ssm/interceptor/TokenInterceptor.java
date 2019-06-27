package com.neusoft.ssm.interceptor;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.neusoft.ssm.bean.User;
import com.neusoft.ssm.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class TokenInterceptor implements HandlerInterceptor{
    //拦截每个请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        //获得请求头部的验证数据
        String token=request.getHeader("Authorization");//Bearer token
        //token不存在
        if(!(null == token||!token.startsWith("Bearer "))){
            token = token.substring(7);
            User loginUser = JwtUtil.unsign(token, User.class);
            if(null != loginUser) {
                //todo update jwt 权限？
                return true;
            }
        }
        //todo
        response.sendRedirect("login");
        return false;
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }

}
