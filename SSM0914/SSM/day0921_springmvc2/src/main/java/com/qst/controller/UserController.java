package com.qst.controller;

import com.alibaba.fastjson.JSON;
import com.qst.pojo.Role;
import com.qst.pojo.User;
import com.qst.service.RoleService;
import com.qst.service.UserService;
import com.qst.util.Constants;
import com.qst.util.Page;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.soap.AttachmentPart;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 18:59 2020/9/22
 * @Modified by:
 */
@Controller
@RequestMapping("/sys/user")
public class UserController {
    /**
     * 跳转页面控制
     * @return
     */
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    @RequestMapping("/list.html")
    public String list(Model model,
                       @RequestParam(value = "queryname", required = false) String userName,
                       @RequestParam(value = "queryUserRole", required = false) String userRoleId,
                       @RequestParam(value = "pageIndex", required = false) String pageIndex
    ) {

//        默认当前页
        Integer currentNo = 1;
//        默认角色id
        Integer _userRoleId = 0;
        //每页数据条数
        Integer pageSize = Constants.pageSize;
        if (userRoleId != null&& userRoleId !="")
            _userRoleId = Integer.valueOf(userRoleId);

        if (pageIndex != null&&pageIndex!="")
            currentNo = Integer.valueOf(pageIndex);
//        总条数
        Integer total = userService.getUserListCount(userName, _userRoleId);
        System.out.println("总条数"+total);
//获取角色
        List<Role> list = roleService.select(null);
//      页面数据
        Page page = new Page(currentNo, pageSize, total);
//获取页面数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("userRoleId", _userRoleId);
        map.put("pageNo", page.getBeginPos());
        map.put("pageSize", pageSize);
        List<User> userList = userService.getuserlist(map);
        for (User user : userList) {
            System.out.println(user);
        }

        System.out.println("记录数"+total);
        System.out.println("全部页面数"+page.getPageCount());
        System.out.println("当前页面数"+currentNo);

        model.addAttribute("roleList", list);
        model.addAttribute("userList", userList);
//      记录数
        model.addAttribute("totalCount",total);
//        全部页面数
        model.addAttribute("totalPageCount",page.getPageCount());
//        当前页面数
        model.addAttribute("currentPageNo",currentNo);
//        页面数据回传
        model.addAttribute("queryUserName",userName);
        model.addAttribute("queryUserRole",_userRoleId);
        return "userlist";
    }


    @RequestMapping(value = "/add.html",method = RequestMethod.GET)
    public String add(@ModelAttribute User user){
        return "useradd";
    }

    //添加用户
    @RequestMapping(value = "/useraddsave.html")
    public String useraddSave(User user, HttpServletRequest request, HttpSession session,
                              @RequestParam(value = "attachs",required = false) MultipartFile[] attach){
//        System.out.println(user.toString());
//        System.out.println(insert);
        String idPicPath = null;
        String workPicPath = null;
        boolean isFile = false;
        String errorInfo = null;
        for (int i = 0; i < attach.length; i++) {
            if (!attach[i].isEmpty()){
                MultipartFile multipartFile = attach[i];
//            获取项目上传路径
                String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadFile");

//            获取原文件名
                String oldfilename = multipartFile.getOriginalFilename();
//            获取文件后缀
                String extension = FilenameUtils.getExtension(oldfilename);
                Integer filiSize = 500000;
                if (i==0){
                    errorInfo="uploadFileError";
//                    request.setAttribute(errorInfo,"文件上传不能超过500K");
//                    return "useradd";
                }else if (i==1){
                    errorInfo="uploadWpError";
//                    request.setAttribute(errorInfo,"文件上传不能超过500K");
//                    return "useradd";
                }
                if (multipartFile.getSize()>filiSize){
//                    errorInfo="uploadWpError";
                    request.setAttribute(errorInfo,"文件上传不能超过500K");
                    return "useradd";
                }
                else if (extension.equalsIgnoreCase("jpg")||extension.equalsIgnoreCase("png")||
                        extension.equalsIgnoreCase("jpeg")||extension.equalsIgnoreCase("pneg")){
                    //生成新文件名
                    String newFileName = System.currentTimeMillis()+ RandomUtils.nextInt(10000000)+"_personal.jpg";
                    File file = new File(path, newFileName);
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    //执行上传
                    try {
                        multipartFile.transferTo(file);
                        isFile=true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        request.setAttribute(errorInfo,"error");
                        return "useradd";
                    }
                }else {
//                    文件格式不正确
                    request.setAttribute(errorInfo,"上传的类型为：jpg，png，jpeg，pneg");
                    return "useradd";
                }
            }


        }

        user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        user.setCreationDate(new Date());
        int insert = userService.insert(user);
        System.out.println(insert);
        return "userlist";
    }

    @RequestMapping(value = "/addUser.html",method = RequestMethod.GET)
    public String addUser(@ModelAttribute("user") User user){
        return "/user/useradd";
    }

    @RequestMapping(value = "/addUser.html",method = RequestMethod.POST)
    public String addUserSave(HttpSession session, @Valid User user, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){

            user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
            user.setCreationDate(new Date());
            int insert = userService.insert(user);
            System.out.println(insert);
            return "redirect:/sys/user/list.html";
        }
        return "user/useradd";

    }



//    @RequestMapping(value = "/modify/{id}")
//    public String modify(@PathVariable String id){
//
//    }

//    @RequestMapping(value = "",method = RequestMethod.GET)
//    @ResponseBody
//    public Object view(@RequestParam(value = "id") String id){
//        User user = userService.login(Long.valueOf(id));
//        return JSON.toJSONString(user);
//    }
}
