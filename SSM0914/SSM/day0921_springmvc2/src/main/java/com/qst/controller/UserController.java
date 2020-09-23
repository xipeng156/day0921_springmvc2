package com.qst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 18:59 2020/9/22
 * @Modified by:
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {
    @RequestMapping("/list.html")
    public String list(){
        return "userlist";
    }
}
