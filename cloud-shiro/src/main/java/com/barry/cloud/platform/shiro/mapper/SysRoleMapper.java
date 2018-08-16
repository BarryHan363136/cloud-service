package com.barry.cloud.platform.shiro.mapper;

import com.barry.cloud.platform.common.mapper.BaseMapper;
import com.barry.cloud.platform.shiro.entity.SysRole;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole, Long> {

    public List<SysRole> findRolesByUserInfo(Map<String, Object> map);

}