package com.barry.cloud.platform.jpa.service.impl;

import com.barry.cloud.platform.jpa.dao.RoleRepository;
import com.barry.cloud.platform.jpa.entity.Role;
import com.barry.cloud.platform.jpa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/26 12:23
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return repository.findRolesByUserId(userId);
    }


}
