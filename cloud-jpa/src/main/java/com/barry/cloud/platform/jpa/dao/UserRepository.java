package com.barry.cloud.platform.jpa.dao;

import com.barry.cloud.platform.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 14:26
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {


}
