package com.barry.cloud.platform.web.service.sys.impl;

import com.barry.cloud.platform.web.entity.sys.User;
import com.barry.cloud.platform.web.mapper.sys.UserMapper;
import com.barry.cloud.platform.web.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/15 11:41
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findList(User user) {
        return userMapper.findResults(null);
    }

    @Override
    public User findUser(String userName) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        List<User> list = userMapper.findUser(map);
        if (list!=null && !list.isEmpty()){
            user = list.get(0);
        }
        return user;
    }


}
