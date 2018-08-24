package com.barry.cloud.platform.jpa.dao;

import com.barry.cloud.platform.jpa.entity.Staff;
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
public interface StaffRepository extends JpaRepository<Staff, Long>, JpaSpecificationExecutor<Staff> {

    /**
     * 根据userName获取staff
     *
     * <@Query是用来配置自定义SQL的注解,后面参数nativeQuery =
     * true才是表明了使用原生的sql,如果不配置,默认是false,则使用HQL查询方式。>
     *
     * @param userName
     * @return
     */
    @Query(value = "select * from spark_staff where userName = ?1", nativeQuery = true)
    List<Staff> findByUserName(String userName);

    /**
     * @Query(value ="select * from employee c "
     *             + "where (c.emp_num=?1 or ?1 is null) and (c.status=?2 or ?2 is null) "
     *             + "order by c.lastupdatetime desc  limit ?3,?4 ",nativeQuery = true)
     *     List<Employee> findAllList(String empNum,String status,Integer pageNumber,Integer pageSize);
     *
     *     @Query(value ="select count(*) from employee c "
     *             + "where (c.emp_num=?1 or ?1 is null) and (c.status=?2 or ?2 is null) "
     *             + "order by c.lastupdatetime desc ",nativeQuery = true)
     *     Integer countAllList(String empNum,String status);
     * */

}
