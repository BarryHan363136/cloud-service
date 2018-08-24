package com.barry.cloud.platform.jpa.controller;

import com.barry.cloud.platform.jpa.entity.Staff;
import com.barry.cloud.platform.jpa.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/23 10:42
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/list")
    public Page<Staff> list(@RequestParam(value = "userName", required = false) String userName,
                            @RequestParam(value = "realName", required = false) String realName,
                            @RequestParam(value = "id", required = false) String id) {
        Staff staff = new Staff();
        staff.setId(StringUtils.isBlank(id) ? null :Long.parseLong(id));
        staff.setUserName(userName);
        staff.setRealName(realName);
        return staffService.findAll(staff, null, null);
    }

    @GetMapping("/results")
    public Page<Staff> results(@RequestParam(value = "userName", required = false) String userName,
                               @RequestParam(value = "realName", required = false) String realName,
                               @RequestParam(value = "id", required = false) String id) {
        Staff staff = new Staff();
        staff.setId(StringUtils.isBlank(id) ? null :Long.parseLong(id));
        staff.setUserName(userName);
        staff.setRealName(realName);
        return staffService.findResult(staff, null, null);
    }

    @GetMapping("/findOne")
    public Staff findOne(@RequestParam(value = "userName", required = false) String userName) {
        return staffService.findOne(userName);
    }


}