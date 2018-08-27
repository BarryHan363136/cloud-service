package com.barry.cloud.platform.security.controller;

import com.barry.cloud.platform.security.entity.User;
import com.barry.cloud.platform.security.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 用户管理Controller
 *
 * @author Tongshan.Han@partner.bmw.com
 * @date 2018/8/27 18:20
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/login", params = {"username", "password"})
    public String getToken(String username, String password) throws AuthenticationException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return "ERRPOR {} 确实必要必要";
        }
        return userService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/register")
    public String register(User user) throws AuthenticationException {
        if (user==null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return "ERRPOR {} 确实必要必要";
        }
        return userService.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @GetMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }

    @GetMapping(value = "/list")
    public List<User> list(HttpServletRequest request) {
        return userService.findUsers(null);
    }

}