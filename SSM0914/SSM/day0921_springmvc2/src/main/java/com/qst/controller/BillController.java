package com.qst.controller;

import com.qst.dao.BillMapper;
import com.qst.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: XiPeng
 * @Description:
 * @Date: Create in 18:24 2020/9/22
 * @Modified by:
 */
@Controller
@RequestMapping("/sys/bill")
public class BillController {
    @RequestMapping("/list.html")
    public String billlist(){
        return "billlist";
    }
}
