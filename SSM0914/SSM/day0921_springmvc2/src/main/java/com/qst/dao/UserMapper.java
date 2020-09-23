package com.qst.dao;

import com.qst.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 14:56 2020/9/17
 * @Modified by:
 */
public interface UserMapper {
    User login(String userCode);
}
