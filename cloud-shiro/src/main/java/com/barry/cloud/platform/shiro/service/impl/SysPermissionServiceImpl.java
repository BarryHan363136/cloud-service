package com.barry.cloud.platform.shiro.service.impl;

import com.barry.cloud.platform.shiro.entity.SysPermission;
import com.barry.cloud.platform.shiro.mapper.SysPermissionMapper;
import com.barry.cloud.platform.shiro.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/17 10:25
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findResults(Map<String, Object> map) {
        return sysPermissionMapper.findResults(map);
    }

    @Override
    public List<SysPermission> findPermissionByRoleInfo(Map<String, Object> map) {
        return sysPermissionMapper.findPermissionByRoleInfo(map);
    }



}
