package com.qst.service.Impl;

import com.qst.dao.UserMapper;
import com.qst.pojo.User;
import com.qst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 15:13 2020/9/17
 * @Modified by:
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
//登录
    @Override
    public User login(String userCode, String passWord) {
        User user = userMapper.login(userCode);
        if (user!=null) {
            if (user.getUserPassword().equals(passWord)) {
                return user;
            }
        }
        return null;
    }
//获取符合条件的用户
    @Override
    public ArrayList<User> getuserlist(HashMap<String, Object> map) {
        return userMapper.getuserlist(map);
    }
//获取查询条数
    @Override
    public int getUserListCount(String userName, Integer userRoleId) {
        return userMapper.getUserListCount(userName,userRoleId);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
