package com.barry.cloud.platform.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/5/3 0003.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@RestController
public class Oauth2ResourceController {

    @RequestMapping(value = "/api")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String success() {
        return "SUCCESS";
    }

}
