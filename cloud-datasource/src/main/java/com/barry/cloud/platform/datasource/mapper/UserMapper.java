package com.barry.cloud.platform.datasource.mapper;

import com.barry.cloud.platform.datasource.base.BaseMapper;
import com.barry.cloud.platform.datasource.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userMapper")
public interface UserMapper extends BaseMapper<User, String> {

}