package com.qst.controller;

import com.qst.pojo.User;
import com.qst.service.UserService;
import com.qst.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 15:00 2020/9/22
 * @Modified by: 用户验证控制器
 */
@Controller
  public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/dologin.html",method = RequestMethod.POST)
    public String dologin(HttpServletRequest request,
                          HttpSession session,
                          @RequestParam(value = "userCode")String userCode,
                          @RequestParam(value = "userPassword")String passWord){

        User user = userService.login(userCode, passWord);
        if (user!=null){
//            throw new RuntimeException("网络异常");
            session.setAttribute(Constants.USER_SESSION,user);
            return "redirect:/main.html";
        }else {
            request.setAttribute("error","用户名和密码不匹配");
            return "login";
        }
    }


    @RequestMapping(value = "/main.html")
    public String login(){
        return "frame";
    }

//    @ExceptionHandler
//    public String handlerException(RuntimeException e,HttpServletRequest request){
//        request.setAttribute("e",e);
//        return "error";
//    }
//退出登录
    @RequestMapping(value = "/logout.html")
    public String logout(HttpSession session){
        session.removeAttribute(Constants.USER_SESSION);
        return "login";
    }
}
