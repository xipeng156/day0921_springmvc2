package com.qst.controller;

import com.qst.pojo.Provider;
import com.qst.pojo.User;
import com.qst.service.ProviderService;
import com.qst.util.Constants;
import com.qst.util.Page;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 18:53 2020/9/22
 * @Modified by:
 */
@Controller
@RequestMapping("/sys/provider")
public class ProviderController {
    @Autowired
    ProviderService providerService;
    @RequestMapping("/list.html")
    public String list(Model model,
                       @RequestParam(value = "queryProCode",required = false)String proCode,
                       @RequestParam(value = "queryProName",required = false)String proName,
                       @RequestParam(value = "pageIndex",required = false)String pageIndex
                                ){
//        默认当前页
        Integer currentNo = 1;
//        每页数据条数
        Integer pageSize = Constants.pageSize;
//        页码处理
        if (pageIndex!=null && pageIndex!="")
            currentNo = Integer.valueOf(pageIndex);
//        总条数
        int count = providerService.getProviderCount(proCode, proName);
//        页面数据
        Page page = new Page(currentNo, pageSize, count);

//        获取页面数据
        Map<String, Object> map = new HashMap<>();
        map.put("proCode",proCode);
        map.put("proName",proName);
        map.put("pageNo",page.getBeginPos());
        map.put("pageSize",pageSize);
        List<Provider> list = providerService.getProvider(map);
        //列表
        model.addAttribute("providerList",list);
        //记录数
        model.addAttribute("totalCount",count);
//        全部页面数
        model.addAttribute("totalPageCount",page.getPageCount());
//        当前页面数
        model.addAttribute("currentPageNo",currentNo);

//        数据回显
        model.addAttribute("queryProCode",proCode);
        model.addAttribute("queryProName",proName);
        return "providerlist";
    }

    @RequestMapping(value = "/add.html",method = RequestMethod.GET)
    public String addProvider(@ModelAttribute User user){
        return "provideradd";
    }
}
