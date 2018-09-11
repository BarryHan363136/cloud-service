package com.barry.cloud.platform.oauth2.service;

import com.barry.cloud.platform.oauth2.entity.User;
import java.util.List;

public interface UserService {

    public List getAuthority();

    public List findAll();

    public User save(User user);

    public void delete(Long id);



}
