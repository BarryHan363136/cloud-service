package com.barry.cloud.platform.oauth2.service.impl;

import com.barry.cloud.platform.oauth2.dao.UserDao;
import com.barry.cloud.platform.oauth2.entity.User;
import com.barry.cloud.platform.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/31 17:14
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userDao.findById(Long.parseLong(userId));
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), getAuthority());
    }

    public List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List findAll() {
        List list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User save(User user) {
        user = userDao.save(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        User user = new User();
        user.setId(id);
        userDao.delete(user);
    }


}