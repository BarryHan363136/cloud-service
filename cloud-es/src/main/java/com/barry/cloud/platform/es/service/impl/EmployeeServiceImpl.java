package com.barry.cloud.platform.es.service.impl;

import com.barry.cloud.platform.es.dao.EmployeeRepository;
import com.barry.cloud.platform.es.entity.Employee;
import com.barry.cloud.platform.es.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;

@Slf4j
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
        //for (Employee employee : employeeList){
            //employeeRepository.save(employee);
        //}
        employeeRepository.saveAll(employeeList);
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
        Employee employee = new Employee();
        employee.setId(id);
        employeeRepository.delete(employee);
    }

    /**
     *
     * QueryBuilder queryBuilder = QueryBuilders.boolQuery()
     *             .must(QueryBuilders.termQuery("user", "kimchy"))
     *             .mustNot(QueryBuilders.termQuery("message", "nihao"))
     *             .should(QueryBuilders.termQuery("gender", "male"));
     * */
    @Override
    public List<Employee> findEmployeesByName(String name, Pageable pageable) {
//        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", name));
//        Page<Employee> page = employeeRepository.search(queryBuilder, pageable);
//        if (page!=null){
//            return page.getContent();
//        }
//        return null;

        // 单个字符串
        QueryBuilder qb0 = QueryBuilders.termQuery("name", name);
        // 闭区间
        //QueryBuilder qb1 = QueryBuilders.rangeQuery("c_time").from(b_time).to(e_time);
        // 大于
        //QueryBuilder qb2 = QueryBuilders.rangeQuery("uid").gt(0);
        // 过滤多条件
        QueryBuilder qb = QueryBuilders.boolQuery().must(qb0);
        Iterable<Employee> aIterable = employeeRepository.search(qb);
        List<Employee> list = new ArrayList<>();
        for (Employee employee : aIterable){
            list.add(employee);
        }
        return list;
    }

    /**
     * 按条件过滤查询
     * @param b_time
     * @param e_time
     * @return
     */
//    public List<ApiLog> findApiLogByDate(String b_time,String e_time) {
//        List<ApiLog> list = new ArrayList<>();
//        try {
//            // 单个字符串
//            //QueryBuilder qb0 = QueryBuilders.termQuery("id", "0");
//            // 闭区间
//            QueryBuilder qb1 = QueryBuilders.rangeQuery("c_time").from(b_time).to(e_time);
//            // 大于
//            QueryBuilder qb2 = QueryBuilders.rangeQuery("uid").gt(0);
//            // 过滤多条件
//            QueryBuilder qb = QueryBuilders.boolQuery().must(qb1).must(qb2);
//
//            Iterable<ApiLog> aIterable = repository.search(qb);
//            for (ApiLog apiLog : aIterable) {
//                list.add(apiLog);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return list;
//    }

}
