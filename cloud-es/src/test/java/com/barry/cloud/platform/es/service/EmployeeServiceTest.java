package com.barry.cloud.platform.es.service;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.es.base.BaseESTest;
import com.barry.cloud.platform.es.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EmployeeServiceTest extends BaseESTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testQueryEmployeeById(){
        String id = "123456";
        Employee employee = employeeService.queryEmployeeById(id);
        if (employee!=null){
            log.info("=================>"+JSONMapper.writeObjectAsString(employee));
        }
    }

    @Test
    public void testAddEmployee(){

    }









}
