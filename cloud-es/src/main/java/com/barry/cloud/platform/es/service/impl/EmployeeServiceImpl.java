package com.barry.cloud.platform.es.service.impl;

import com.barry.cloud.platform.es.dao.EmployeeRepository;
import com.barry.cloud.platform.es.entity.Employee;
import com.barry.cloud.platform.es.service.EmployeeService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import com.google.common.collect.Lists;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void saveList(List<Employee> employeeList) {
        for (Employee employee : employeeList){
            employeeRepository.save(employee);
        }
    }

    @Override
    public Employee queryEmployeeById(String id) {
        return employeeRepository.queryEmployeeById(id);
    }

    @Override
    public List<Employee> findEmployeesByDepartment(String department) {
        List<Employee> list = employeeRepository.findEmployeesByDepartment(department);
        return list;
    }

    @Override
    public List<Employee> findEmployeesByDepartment(String department, Pageable pag) {
        /** 按标题进行搜索 */
        QueryBuilder builder = QueryBuilders.matchQuery("department", department);
        /** 如果实体和数据的名称对应就会自动封装，pageable分页参数 */
        Iterable<Employee> listIt =  employeeRepository.search(builder, pag);
        /** Iterable转list */
        List<Employee> list = Lists.newArrayList(listIt);
        return list;
    }

    @Override
    public void delete(String id) {
        employeeRepository.delete(new Employee(id));
    }
}
