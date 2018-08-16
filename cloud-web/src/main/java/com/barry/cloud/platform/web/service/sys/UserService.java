package com.barry.cloud.platform.web.service.sys;

import com.barry.cloud.platform.web.entity.sys.User;
import java.util.List;

public interface UserService {

    public List<User> findList(User user);

    public User findUser(String userName);

}
