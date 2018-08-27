package com.barry.cloud.platform.security.dao;

import com.barry.cloud.platform.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/27 16:57
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @Query(value = "select a.* from spark_role a LEFT JOIN spark_user_role b on a.id = b.rid LEFT JOIN spark_user c on b.uid = c.id where c.id = ?1 order by a.id", nativeQuery = true)
    List<Role> findRolesByUid(Long uid);


}
