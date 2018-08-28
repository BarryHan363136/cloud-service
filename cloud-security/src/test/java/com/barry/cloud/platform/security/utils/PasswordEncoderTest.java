package com.barry.cloud.platform.security.utils;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.security.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/28 11:06
 */
@Slf4j
public class PasswordEncoderTest {

    @Test
    public void testEncoder(){
        User user = new User();
        user.setPassword("123456");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        log.info("=========encodePass==========>"+JSONMapper.writeObjectAsString(user));
    }

    @Test
    public void testEncoder2(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String enpass = encoder.encode(rawPassword);
        log.info("=========encodePass==========>"+enpass);
    }


}
