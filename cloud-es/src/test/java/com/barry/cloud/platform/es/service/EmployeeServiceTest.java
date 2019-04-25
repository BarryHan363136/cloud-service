package com.barry.cloud.platform.es.service;

import com.barry.cloud.platform.common.id.IdWorker;
import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.es.base.BaseESTest;
import com.barry.cloud.platform.es.dao.EmployeeRepository;
import com.barry.cloud.platform.es.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class EmployeeServiceTest extends BaseESTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testQueryEmployeeById(){
        String id = "203785090214396935";
        Employee employee = employeeService.queryEmployeeById(id);
        if (employee!=null){
            log.info("=================>"+JSONMapper.writeObjectAsString(employee));
        }
    }

    @Test
    public void testQueryEmployeesByDepartment(){
        String department = "业务部";
        List<Employee> employeeList = employeeService.findEmployeesByDepartment(department);
        if (employeeList!=null && !employeeList.isEmpty()){
            log.info("=================>"+JSONMapper.writeObjectAsString(employeeList));
        }
    }

//    @Test
//    public void testQueryEmployeesByDepartment2(){
//        String department = "业务部";
//        Integer currentPage = 1;
//        Integer pageSize = 10;
//        /** 按标题进行搜索 */
//        QueryBuilder builder = QueryBuilders.matchQuery("department", department);
//        Page<Employee> tPage = employeeRepository.search(builder, new PageRequest(currentPage, pageSize));
//        if (tPage!=null){
//            log.info("=============getTotalPages==========>"+tPage.getTotalPages());
//            log.info("=============getTotalElements==========>"+tPage.getTotalElements());
//            log.info("=============getContent==========>"+JSONMapper.writeObjectAsString(tPage.getContent()));
//
//            List<Employee> employeeList = tPage.getContent();
//            if (employeeList!=null && !employeeList.isEmpty()){
//                log.info("=================>"+JSONMapper.writeObjectAsString(employeeList));
//            }
//        }
//    }

    @Test
    public void testBatchAddEmployee(){
        IdWorker idWorker = new IdWorker(1L);
        List<Employee> employeeList = new ArrayList<>();
        for (int i=0;i<10;i++){
            Employee employee = new Employee();
            employee.setId(idWorker.nextId().toString());
            employee.setName("张三"+i);
            employee.setSex("男");
            employee.setAge(30);
            employee.setMobile("1598635421"+i);
            employee.setAddress("上海市虹口区XXX路"+(i+1)+"号");
            employee.setDepartment("业务部");
            employee.setPosition("普通员工");
            employee.setBirthDay(new Date());
            employee.setCardNo("61062219750215842"+i);
            employeeList.add(employee);
        }
        employeeService.saveList(employeeList);
    }

    @Test
    public void testSave(){
        IdWorker idWorker = new IdWorker(2L);
        Employee employee = new Employee();
        employee.setId(idWorker.nextId().toString());
        employee.setName("张三"+"001");
        employee.setSex("男");
        employee.setAge(30);
        employee.setMobile("1598635421"+"001");
        employee.setAddress("上海市虹口区XXX路001号");
        employee.setDepartment("业务部");
        employee.setPosition("普通员工");
        employee.setBirthDay(new Date());
        employee.setCardNo("61062219750215842"+"001");
        log.info("=================>"+JSONMapper.writeObjectAsString(employee));
    }







}
