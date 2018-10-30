package com.barry.cloud.platform.routing.service;

import com.barry.cloud.platform.routing.entity.Staff;

import java.util.List;

public interface StaffService {


    public Integer save(Staff staff);


    public Staff getStaffById(Long id);


    public List<Staff> getStaffList(Staff staff);


}
