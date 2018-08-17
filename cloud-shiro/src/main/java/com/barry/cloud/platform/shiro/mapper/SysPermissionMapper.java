package com.barry.cloud.platform.shiro.mapper;

import com.barry.cloud.platform.common.mapper.BaseMapper;
import com.barry.cloud.platform.shiro.entity.SysPermission;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission, Long> {

    public List<SysPermission> findPermissionByRoleInfo(Map<String, Object> map);

}