package com.barry.cloud.platform.routing.service;

import com.barry.cloud.platform.routing.base.BaseTest;
import com.barry.cloud.platform.routing.common.utils.JSONMapper;
import com.barry.cloud.platform.routing.entity.Staff;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author Tongshan.Han@partner.bmw.com
 * @Date 2018/10/31 11:19
 */
@Slf4j
public class StaffServiceTest extends BaseTest {

    @Autowired
    private StaffService staffService;

    @Test
    public void getStaff(){
        Long id = 1L;
        Staff staff = staffService.getStaffById(id);
        log.info("============>"+JSONMapper.writeObjectAsString(staff));
    }

    @Test
    public void model(){
        Staff staff = new Staff();
        staff.setId(3L);
        staff.setName("好人一生平安");
        staff.setGender((short) 0);
        staff.setMobile("15963585425");
        staff.setAddress("上海市闵行区七莘路");
        staff.setRemark("测试数据3L");
        log.info("============>"+JSONMapper.writeObjectAsString(staff));
    }








}
