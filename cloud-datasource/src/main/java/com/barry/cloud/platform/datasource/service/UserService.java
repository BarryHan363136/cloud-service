package com.barry.cloud.platform.datasource.service;

import com.barry.cloud.platform.datasource.entity.User;
import java.util.List;

public interface UserService {

    public List<User> findUsers(User user);

}
