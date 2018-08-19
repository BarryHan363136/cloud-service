package com.barry.cloud.platform.shiro.controller;

import com.barry.cloud.platform.shiro.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
public class HomeController {

    @RequestMapping({"/turnLogin"})
    public String turnLogin(){
        return"/login";
    }

    @RequestMapping({"/index"})
    public String index(){
        return"/index";
    }

//    @RequestMapping("/login")
//    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
//        log.info("HomeController.login()");
//        /** 登录失败从request中获取shiro处理的异常信息 */
//        /** shiroLoginFailure:就是shiro异常类的全类名. */
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//        log.info("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                log.info("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                log.info("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                log.info("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> "+exception;
//                log.info("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
//        /** 此方法不处理登录成功,由shiro进行处理 */
//        return "/login";
//    }
//
//    @RequestMapping("/403")
//    public String unauthorizedRole(){
//        log.info("HomeController /403 ------没有权限-------");
//        return "403";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        UserInfo userInfo) {
        if (StringUtils.isEmpty(userInfo.getUsername()) || StringUtils.isEmpty(userInfo.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "/login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        try {
            subject.login(token);
            return "redirect:index";
        } catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "/login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "用户或密码不正确！");
            return "/login";

        }

    }

}