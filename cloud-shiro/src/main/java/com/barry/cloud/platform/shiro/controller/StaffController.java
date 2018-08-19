package com.barry.cloud.platform.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StaffController {

    /**
     * 用户查询.
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("staff:list")
    public String userInfo(){
        return "staffList";
    }

    /**
     * 用户添加;
     * @return
     */
    @GetMapping("/add")
    @RequiresPermissions("staff:add")
    public String userInfoAdd(){
        return "staffAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @GetMapping("/del")
    @RequiresPermissions("staff:del")
    public String userDel(){
        return "staffDel";
    }
}