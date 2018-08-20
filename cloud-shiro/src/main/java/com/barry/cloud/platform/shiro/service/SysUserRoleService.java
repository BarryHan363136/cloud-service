package com.barry.cloud.platform.shiro.service;

import com.barry.cloud.platform.shiro.entity.SysUserRole;
import java.util.List;
import java.util.Map;

public interface SysUserRoleService {

    public Integer insert(SysUserRole sysUserRole);

    public Integer delete(Long roleId, Long userId);

    public Integer batchSave(List<SysUserRole> list);

    public List<SysUserRole> findResults(Map<String, Object> map);


}
