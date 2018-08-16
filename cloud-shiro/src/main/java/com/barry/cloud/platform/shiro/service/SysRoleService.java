package com.barry.cloud.platform.shiro.service;

import com.barry.cloud.platform.shiro.entity.SysRole;
import java.util.List;

public interface SysRoleService {

    public List<SysRole> findRolesByUserName(String userName);

}
