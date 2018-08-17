package com.barry.cloud.platform.shiro.service;

import com.barry.cloud.platform.shiro.entity.SysPermission;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/17 10:20
 */
public interface SysPermissionService {

    public List<SysPermission> findPermissionByRoleInfo(String roleId);

}
