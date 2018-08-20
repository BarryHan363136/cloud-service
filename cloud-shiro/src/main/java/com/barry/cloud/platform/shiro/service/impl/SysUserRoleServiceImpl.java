package com.barry.cloud.platform.shiro.service.impl;

import com.barry.cloud.platform.shiro.entity.SysUserRole;
import com.barry.cloud.platform.shiro.mapper.SysUserRoleMapper;
import com.barry.cloud.platform.shiro.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 10:22
 */
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Integer insert(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public Integer delete(Long roleId, Long userId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId);
        sysUserRole.setUid(userId);
        return sysUserRoleMapper.delete(sysUserRole);
    }

    @Override
    public Integer batchSave(List<SysUserRole> list) {
        return sysUserRoleMapper.insertList(list);
    }

    @Override
    public List<SysUserRole> findResults(Map<String, Object> map) {
        sysUserRoleMapper.selectByExample(map);
        sysUserRoleMapper.selectCountByExample(map);
        return null;
    }






}
