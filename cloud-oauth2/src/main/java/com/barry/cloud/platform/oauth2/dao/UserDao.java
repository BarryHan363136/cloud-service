package com.barry.cloud.platform.oauth2.dao;

import com.barry.cloud.platform.oauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/31 17:17
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

//    @Query(value = "select * from spark_user where username = ?1", nativeQuery = true)
//    User findbyUserName(String username);

}
