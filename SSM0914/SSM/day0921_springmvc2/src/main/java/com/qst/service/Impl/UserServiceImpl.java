package com.qst.service.Impl;

import com.qst.dao.UserMapper;
import com.qst.pojo.User;
import com.qst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}
