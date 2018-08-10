package com.barry.cloud.platform.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/10 17:50
 */
@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String index(){
        return "/views/index";
    }

    @GetMapping(value = "/welcome")
    public ModelAndView welcome(){
        return new ModelAndView("/views/welcome");
    }

}