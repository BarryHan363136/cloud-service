package com.barry.cloud.platform.jpa.service;

import com.barry.cloud.platform.jpa.entity.Role;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 11:14
 */
public interface RoleService {

    public List<Role> findRolesByUserId(Long userId);

}
