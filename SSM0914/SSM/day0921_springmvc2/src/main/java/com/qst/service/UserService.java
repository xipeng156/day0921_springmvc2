package com.qst.service;

import com.qst.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 15:13 2020/9/17
 * @Modified by:
 */
public interface UserService {
    User login(String userCode,String passWord);
    ArrayList<User> getuserlist(HashMap<String,Object> map);

    public int getUserListCount(String userName,Integer userRoleId);
    int insert(User user);
}
