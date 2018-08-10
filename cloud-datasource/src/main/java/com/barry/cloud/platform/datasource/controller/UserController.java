package com.barry.cloud.platform.datasource.controller;

import com.barry.cloud.platform.datasource.entity.User;
import com.barry.cloud.platform.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/10 10:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/list")
    public List<User> list(HttpServletRequest request) {
        return userService.findUsers(null);
    }


}
