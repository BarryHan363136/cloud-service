package com.barry.cloud.platform.routing.service.impl;

import com.barry.cloud.platform.routing.entity.Staff;
import com.barry.cloud.platform.routing.mapper.StaffMapper;
import com.barry.cloud.platform.routing.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description:
 * @Author Tongshan.Han@partner.bmw.com
 * @Date 2018/10/30 22:20
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public Integer save(Staff staff) {
        return staffMapper.insert(staff);
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Staff> getStaffList(Staff staff) {
        return staffMapper.findResults(null);
    }

}
