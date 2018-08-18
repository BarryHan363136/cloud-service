package com.barry.cloud.platform.shiro.service;

import com.barry.cloud.platform.shiro.entity.SysRole;
import java.util.List;
import java.util.Map;

public interface SysRoleService {

    public List<SysRole> findRolesByUserInfo(Map<String, Object> map);

}
