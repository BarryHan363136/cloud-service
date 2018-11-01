package com.barry.cloud.platform.routing.controller;

import com.barry.cloud.platform.routing.entity.Staff;
import com.barry.cloud.platform.routing.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author Tongshan.Han@partner.bmw.com
 * @Date 2018/10/31 15:26
 */
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping(value = "/getStaffById")
    public Staff getStaffById(HttpServletRequest request) {
        String sid = request.getParameter("id");
        if (StringUtils.isBlank(sid)){
            log.warn("getStaffById missing request parameter id");
            return null;
        }
        Staff staff = staffService.getStaffById(Long.parseLong(sid));
        return staff;
    }

    /**
     * {"id":3,"name":"好人一生平安","gender":0,"mobile":"15963585425","address":"上海市闵行区七莘路","remark":"测试数据3L"}
     *
     * */
    @PostMapping(value = "/save")
    public Staff save(@RequestBody Staff staff) {
        staffService.save(staff);
        return staff;
    }






}
