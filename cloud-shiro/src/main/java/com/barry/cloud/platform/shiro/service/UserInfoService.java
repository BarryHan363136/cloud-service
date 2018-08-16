package com.barry.cloud.platform.shiro.service;

import com.barry.cloud.platform.shiro.entity.UserInfo;

public interface UserInfoService {

    public UserInfo findByUsername(String userName);

}
