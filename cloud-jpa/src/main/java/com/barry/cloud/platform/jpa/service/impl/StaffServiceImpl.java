package com.barry.cloud.platform.jpa.service.impl;

import com.barry.cloud.platform.jpa.dao.StaffRepository;
import com.barry.cloud.platform.jpa.entity.Staff;
import com.barry.cloud.platform.jpa.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 14:29
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Staff save(Staff user) {
        return staffRepository.save(user);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff findOne(String userName) {
        return staffRepository.findByUserName(userName);
    }

    @Override
    public boolean delete(Long id) {
        Staff staff = new Staff();
        staff.setId(id);
        staffRepository.delete(staff);
        return true;
    }



}
