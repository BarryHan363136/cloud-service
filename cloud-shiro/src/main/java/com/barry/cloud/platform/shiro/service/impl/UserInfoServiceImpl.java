package com.barry.cloud.platform.shiro.service.impl;

import com.barry.cloud.platform.shiro.entity.UserInfo;
import com.barry.cloud.platform.shiro.mapper.UserInfoMapper;
import com.barry.cloud.platform.shiro.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/16 17:44
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findByUsername(String userName) {
        UserInfo user = null;
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        List<UserInfo> list = userInfoMapper.findByUsername(map);
        if (list!=null && !list.isEmpty()){
            user = list.get(0);
        }
        return user;
    }
}
