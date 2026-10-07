package com.atguigu.headline.controller;

import com.atguigu.headline.common.Result;
import com.atguigu.headline.common.ResultCodeEnum;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.service.impl.NewsUserServiceImpl;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.MD5Util;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{
   private NewsUserService newsUserService =new NewsUserServiceImpl();

    /**
     * 处理登录表单提交的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名和密码
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);
        //调用服务层方法

        Result result= null;
        NewsUser loginUser=newsUserService.findByUsername(paramUser.getUsername());
        if(loginUser!=null){
            if (MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())) {
                Map data = new HashMap();
                data.put("token", JwtHelper.createToken(loginUser.getUid().longValue()));
                result = Result.ok(data);
            }else {
                result = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
            }
        }else {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        //响应给客户端
        WebUtil.writeJson(resp, result);

    }


    /**
     * 根据token获取用户信息的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受请求中的token
        String token = req.getHeader("token");
        Result result= Result.build(null,ResultCodeEnum.NOTLOGIN);
        //校验token
        if(token!=null && !token.equals("")){
            if (! JwtHelper.isExpiration(token)) {
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser =newsUserService.findByUid(userId);
                //通过校验返回 将用户信息放入Result
                if (newsUser != null) {
                    Map data = new HashMap();
                    newsUser.setUserPwd("");
                    data.put("loginUser",newsUser);
                    result = Result.ok(data);
                }

            }
        }

       WebUtil.writeJson(resp, result);

    }


    /**
     * 校验用户名是否被占用的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void  checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受用户名参数
        String username = req.getParameter("username");

        //调用服务层校验
        NewsUser newsUser = newsUserService.findByUsername(username);
        Result result=null;
        if(newsUser ==null){
            result = Result.ok(null);
        }else {
            result= Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        //做出响应
        WebUtil.writeJson(resp, result);
    }

    /**
     * 注册功能接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        NewsUser username = newsUserService.findByUsername(newsUser.getUsername());
        Result result=null;
        if (username == null) {
            newsUserService.regist(newsUser);
            result = Result.ok(null);
        }else {
            result= Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }


    /**
     * 通过token检验用户登录是否过期
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result =Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(null!= token){
            if (!JwtHelper.isExpiration(token)) {
                result=Result.ok(null);
            }
        }
        WebUtil.writeJson(resp,result);
    }
}
