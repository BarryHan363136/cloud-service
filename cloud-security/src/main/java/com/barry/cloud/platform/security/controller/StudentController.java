package com.barry.cloud.platform.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

/**
 * ROLE_USER权限
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/23 10:42
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    /**
     * 以下两种注解都可以
     * @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     * @Secured({"ROLE_ADMIN"})
     * */
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/list")
    public List list() {
        return Arrays.asList("student-小明", "student-小王", "student-小张");
    }



}