package com.barry.cloud.platform.security.dao;

import com.barry.cloud.platform.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/27 16:57
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findUserByUsername(String username);

}
