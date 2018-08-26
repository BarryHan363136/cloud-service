package com.barry.cloud.platform.jpa.service.impl;

import com.barry.cloud.platform.jpa.dao.UserRepository;
import com.barry.cloud.platform.jpa.entity.User;
import com.barry.cloud.platform.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/25 11:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUser(String userName) {
        return null;
    }

}
