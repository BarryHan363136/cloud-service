package com.barry.cloud.platform.shiro.service.impl;

import com.barry.cloud.platform.shiro.entity.SysRole;
import com.barry.cloud.platform.shiro.mapper.SysRoleMapper;
import com.barry.cloud.platform.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/16 18:00
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findRolesByUserInfo(Map<String, Object> map) {
        return sysRoleMapper.findRolesByUserInfo(map);
    }



}
