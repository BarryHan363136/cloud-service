package com.barry.cloud.platform.jpa.service;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.jpa.base.BaseTest;
import com.barry.cloud.platform.jpa.entity.Role;
import com.barry.cloud.platform.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 11:16
 */
@Slf4j
public class UserServiceTest extends BaseTest {

    @Test
    public void testGetRolesByUsr(){
        User user = new User();
        user.setId(1L);
        List<Role> roles = user.getRoles();
        log.info("==================>"+JSONMapper.writeObjectAsString(roles));
    }

}
