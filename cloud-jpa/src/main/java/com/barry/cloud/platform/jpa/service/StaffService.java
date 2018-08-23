package com.barry.cloud.platform.jpa.service;

import com.barry.cloud.platform.jpa.entity.Staff;
import org.springframework.data.domain.Page;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 14:28
 */
public interface StaffService {

    Staff save(Staff user);

    Page<Staff> findAll(Staff staff, Integer pageNumber, Integer pageSize);

    Staff findOne(String userName);

    boolean delete(Long id);

}
