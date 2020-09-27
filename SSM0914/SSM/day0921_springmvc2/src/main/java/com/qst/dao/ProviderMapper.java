package com.qst.dao;

import com.qst.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 17:30 2020/9/19
 * @Modified by:
 */
public interface ProviderMapper {
    public int insert(Provider provider);
    public int update(Provider provider);
    public int delete(int id);
    public List<Provider> select(HashMap<String, Object> map);
    int getProviderCount(@Param(value = "proCode") String proCode,
                         @Param(value = "proName") String proName);
    List<Provider> getProvider(Map<String,Object> map);
}
