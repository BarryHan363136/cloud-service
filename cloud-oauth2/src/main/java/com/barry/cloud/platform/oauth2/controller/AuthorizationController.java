package com.barry.cloud.platform.oauth2.controller;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.oauth2.entity.User;
import com.barry.cloud.platform.oauth2.properties.OAuth2Properties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/31 17:12
 */
@Slf4j
@RestController
public class AuthorizationController {

    @Autowired
    private OAuth2Properties oAuth2Properties;

    @GetMapping("/userJwt")
    public Object getCurrentUserJwt(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        log.info("【SecurityOauth2Application】 getCurrentUserJwt authentication={}", JSONMapper.writeObjectAsString(authentication));

        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");

        Claims claims = Jwts.parser().setSigningKey(oAuth2Properties.getJwtSigningKey().getBytes("UTF-8")).parseClaimsJws(token).getBody();
        String blog = (String) claims.get("blog");
        log.info("【SecurityOauth2Application】 getCurrentUser1 blog={}", blog);

        return authentication;
    }

    @GetMapping("/userRedis")
    public Object getCurrentUserRedis(Authentication authentication) {
        log.info("【SecurityOauth2Application】 getCurrentUserRedis authentication={}", JSONMapper.writeObjectAsString(authentication));


        return authentication;
    }

    @GetMapping("/user/me")
    public Principal user(Principal user){
        return user;
    }

    @GetMapping(value = "/list")
    public List listUser(){
        ConcurrentHashMap hashMap = new ConcurrentHashMap();
        User user = new User();
        user.setId(1000L);
        user.setUserName("张三");
        user.setUserPwd("123456");
        user.setGender(1);
        user.setStatus(1);

        User user2 = new User();
        user.setId(1001L);
        user.setUserName("李四");
        user.setUserPwd("123456");
        user.setGender(0);
        user.setStatus(0);

        hashMap.put("1000", user);
        hashMap.put("1000", user2);

        return Arrays.asList(hashMap);
    }

}