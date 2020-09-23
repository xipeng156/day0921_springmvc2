package com.qst.service;

import com.qst.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 15:13 2020/9/17
 * @Modified by:
 */
public interface UserService {
    User login(String userCode,String passWord);
}
