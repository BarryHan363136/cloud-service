package com.barry.cloud.platform.shiro.mapper;

import com.barry.cloud.platform.common.mapper.BaseMapper;
import com.barry.cloud.platform.shiro.entity.UserInfo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo, Long> {

    public List<UserInfo> findByUsername(Map<String, Object> map);

}