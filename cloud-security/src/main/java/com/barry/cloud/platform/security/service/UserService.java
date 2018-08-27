package com.barry.cloud.platform.security.service;

import com.barry.cloud.platform.security.entity.User;

import java.util.List;

/**
 * 用户操作接口
 *
 * @author Tongshan.Han@partner.bmw.com
 * @date 2018/8/27 18:05
 *
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    String register(User user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);

    List<User> findUsers(User user);

}