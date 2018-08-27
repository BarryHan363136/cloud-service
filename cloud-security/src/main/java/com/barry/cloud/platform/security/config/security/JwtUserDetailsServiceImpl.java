package com.barry.cloud.platform.security.config.security;

import com.barry.cloud.platform.security.dao.RoleRepository;
import com.barry.cloud.platform.security.dao.UserRepository;
import com.barry.cloud.platform.security.domain.JwtUser;
import com.barry.cloud.platform.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户验证方法
 *
 * @author Tongshan.Han@partner.bmw.com
 * @date 2018/8/27 17:39
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        user.setRoles(roleRepository.findRolesByUid(user.getId()));
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<String> roles = new ArrayList<>();
            user.getRoles().stream().map(e -> roles.add(e.getName()));
            Collection<? extends GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            return new JwtUser(user.getUsername(), user.getPassword(), authorities);
        }
    }

}