package com.barry.cloud.platform.jpa.service;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.jpa.base.BaseTest;
import com.barry.cloud.platform.jpa.entity.Staff;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 14:37
 */
@Slf4j
public class StaffServiceTest extends BaseTest {

    @Autowired
    private StaffService staffService;

    @Test
    public void testSaveStaff(){
        Staff staff = new Staff();
        staff.setId(1000L);
        staff.setUserName("zhangsan");
        staff.setPassword("123456");
        staff.setRealName("张三");
        staff.setGender(0);
        Staff staff2 = staffService.save(staff);
        log.info("====================>"+JSONMapper.writeObjectAsString(staff2));
    }






}
