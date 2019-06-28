package com.neusoft.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.neusoft.ssm.bean.User;
import com.neusoft.ssm.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


public class TokenInterceptor implements HandlerInterceptor{
    /*
     * @Description 拦截每个请求,判断是否登录
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
                String servletPath = request.getServletPath();//    /outpatientDoctorWorkstation/searchPatient/1
                if(servletPath.startsWith("/outpatientDoctorWorkstation")||servletPath.startsWith("/setManage")||servletPath.startsWith("/medicalRecordHome")
                        ||servletPath.startsWith("/applyForPrescription")||servletPath.startsWith("/applyForFmeditem")){//门诊医生权限能进的controller
                    if(loginUser.getUserCategory().equals("2"))
                        updateToken(response, loginUser);
                    else {//权限不符
                        setFailureResponse(request, response);
                        return false;
                    }
                }else {//todo 往里加
                    setFailureResponse(request, response);//权限都不对
                }
                return true;
            }
        }
        setFailureResponse(request, response);
        return false;
    }
    //更新respond里的token
    private void updateToken(HttpServletResponse response,User loginUser){
        //update token
        String newToken = JwtUtil.sign(loginUser, 60L* 1000L* 30L);
        response.setHeader("Authorization",newToken);
    }
    //分ajax或普通页面进行返回
    private void setFailureResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //如果是ajax请求响应头会有x-requested-with
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            response.setStatus(401);//ajax 返回状态码，进入eror
        }else {//非ajax，跳转到login.jsp
            response.sendRedirect(request.getContextPath()+"/loginController/loginFailure");
        }
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }

}
