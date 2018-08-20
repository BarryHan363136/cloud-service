package com.barry.cloud.platform.security.jwt.service.impl;

import com.barry.cloud.platform.security.jwt.entity.User;
import com.barry.cloud.platform.security.jwt.mapper.UserMapper;
import com.barry.cloud.platform.security.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 17:47
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        User user = new User();
        user.setUserName(username);
        List<User> list = userMapper.select(user);
        if (list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

}
