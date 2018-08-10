package com.barry.cloud.platform.datasource.service.impl;

import com.barry.cloud.platform.datasource.entity.User;
import com.barry.cloud.platform.datasource.mapper.UserMapper;
import com.barry.cloud.platform.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/10 11:16
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUsers(User user) {
        return userMapper.findResults(null);
    }

}
