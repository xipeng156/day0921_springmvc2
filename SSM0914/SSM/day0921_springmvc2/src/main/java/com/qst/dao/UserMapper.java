package com.qst.dao;

import com.qst.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 14:56 2020/9/17
 * @Modified by:
 */
public interface UserMapper {
//
//    @Param(value = "pageNo")Integer pageNo,
//    @Param(value = "pageSize")Integer pageSize
    User login(String userCode);
    ArrayList<User> getuserlist(HashMap<String,Object> map);
    //根据条件查询用户的总条数
    public int getUserListCount(@Param(value = "userName") String userName,
                                @Param(value = "userRoleId")Integer userRoleId);
    int insert(User user);
}
