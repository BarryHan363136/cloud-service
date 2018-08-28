package com.barry.cloud.platform.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/23 10:42
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/list")
    public List list() {
        return Arrays.asList("staff-苹果", "staff-香蕉", "staff-榴莲");
    }



}