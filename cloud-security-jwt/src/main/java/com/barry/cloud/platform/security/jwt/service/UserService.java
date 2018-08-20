package com.barry.cloud.platform.security.jwt.service;

import com.barry.cloud.platform.security.jwt.entity.User;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 14:28
 */
public interface UserService {

    User findByUsername(String username);

}
