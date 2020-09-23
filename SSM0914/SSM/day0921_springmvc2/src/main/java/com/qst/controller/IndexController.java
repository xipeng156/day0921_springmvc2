package com.qst.controller;

import com.qst.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 10:28 2020/9/21
 * @Modified by:
 */
@Controller
public class IndexController{
    @RequestMapping("/request")
    public String request(HttpServletRequest request){
        request.setAttribute("name","王五");
        return "index";
    }
    @RequestMapping("/session")
    public String session(HttpSession httpSession){
        httpSession.setAttribute("name","李四");
        return "index";
    }
    @RequestMapping("getUsername")
    public String getUsername(String username){
        System.out.println("username:"+username);
        return "success";
    }

    /**
     * required 默认为true 置为false即可为空
     * @param username
     * @return
     */
    @RequestMapping(value = "/getOne",method = RequestMethod.POST,params ="uname")
    public ModelAndView getUname(@RequestParam(value = "uname") String username){
        System.out.println("==================="+username);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username",username);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/saveName",method = RequestMethod.GET,params = "userName")
    public String saveName(@RequestParam(value = "userName",required = false)String userName, Model model){
        model.addAttribute("userName",userName);
        return "index";
    }


    @RequestMapping(value = "/saveUserName",method = RequestMethod.GET,params = "userName")
    public String saveUserName(@RequestParam(value = "userName",required = false)String userName, Map<String,Object> userMap){
        userMap.put("userName",userName);
        return "index";
    }

//    @RequestMapping(value = "/saveUser",method = RequestMethod.GET,params = "userName")
//    public String saveUser(@RequestParam(value = "userName",required = false)String userName,Model model){
////        User user = new User();
//////        user.setUname(userName);
////        model.addAttribute("user",user);
////        return "index";
//    }
}
