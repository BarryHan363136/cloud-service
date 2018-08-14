package com.barry.cloud.platform.web.controller.sys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/10 17:50
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "/list")
    public ModelAndView index(){
        return new ModelAndView("main");
    }

    @GetMapping(value = "/add")
    public ModelAndView homepage(){
        return new ModelAndView("homepage");
    }

    @GetMapping(value = "/del")
    public ModelAndView logout(){
        return new ModelAndView("login");
    }


}