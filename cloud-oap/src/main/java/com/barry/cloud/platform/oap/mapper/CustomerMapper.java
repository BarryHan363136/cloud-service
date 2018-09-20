package com.barry.cloud.platform.oap.mapper;

import com.barry.cloud.platform.common.mapper.RepositoryMapper;
import com.barry.cloud.platform.oap.entity.Customer;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper extends RepositoryMapper<Customer> {

    @Select("select * from spark_customer where id = #{id}")
    Customer find(@Param("id") Long id);

}
