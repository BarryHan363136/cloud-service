package com.barry.cloud.platform.security.jwt.service;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.security.jwt.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 17:51
 */
@Slf4j
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void testQuery(){
        User user = userService.findByUsername("zhangsan");
        log.info("========================>"+JSONMapper.writeObjectAsString(user));
    }


}
