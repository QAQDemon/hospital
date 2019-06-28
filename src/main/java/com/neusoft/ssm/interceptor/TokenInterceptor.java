package com.neusoft.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.neusoft.ssm.bean.User;
import com.neusoft.ssm.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class TokenInterceptor implements HandlerInterceptor{
    /*
     * @Description 拦截每个请求,判断是否登录//TODO
     * @Param [request, response, handler]
     * @return boolean
     **/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获得请求头部的验证数据
        String token=request.getHeader("Authorization");//Bearer token
        //token不存在
        if(!(null == token||!token.startsWith("Bearer "))){
            token = token.substring(7);
            User loginUser = JwtUtil.unsign(token, User.class);
            if(null != loginUser) {
                //todo  jwt 权限？
                //update token
                String newToken = JwtUtil.sign(loginUser, 60L* 1000L* 30L);
                response.setHeader("Authorization",newToken);
                return true;
            }
        }
        //如果是ajax请求响应头会有x-requested-with
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            response.setStatus(401);//ajax 返回状态码，进入eror
        }else {//非ajax，跳转到login.jsp
            response.sendRedirect(request.getContextPath()+"/loginController/loginFailure");
        }
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
