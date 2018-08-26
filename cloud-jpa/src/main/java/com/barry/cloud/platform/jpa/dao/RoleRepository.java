package com.barry.cloud.platform.jpa.dao;

import com.barry.cloud.platform.jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/20 14:26
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @Query("select a.id,a.name from spark_role a LEFT JOIN spark_user_role b on a.id = b.rid LEFT JOIN spark_user c on b.uid = c.id where c.id = ?1 GROUP BY a.id")
    List<Role> findRolesByUserId(Long userId);

}
