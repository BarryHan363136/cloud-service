package com.barry.cloud.platform.es.dao;

import com.barry.cloud.platform.es.entity.Employee;
import com.github.pagehelper.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {

    Employee queryEmployeeById(String id);

    List<Employee> findEmployeesByDepartment(String department);

    /**
     * 使用 Page<Country> countrys = countrySearchRepository.findByName("测试",  PageRequest.of(0, 10)); //分页是从0开始的
     * */
    Page<Employee> findEmployeesByDepartment(String department, Pageable pageable);


}
