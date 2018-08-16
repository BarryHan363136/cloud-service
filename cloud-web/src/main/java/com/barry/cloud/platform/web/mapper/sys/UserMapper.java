package com.barry.cloud.platform.web.mapper.sys;

import com.barry.cloud.platform.common.mapper.BaseMapper;
import com.barry.cloud.platform.web.entity.sys.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User, Long> {

    public List<User> findUser(Map<String, Object> map);

}