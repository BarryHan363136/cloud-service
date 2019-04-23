package com.barry.cloud.platform.es.controller;

import com.barry.cloud.platform.common.id.IdWorker;
import com.barry.cloud.platform.es.entity.Employee;
import com.barry.cloud.platform.es.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //增加
    @RequestMapping("/add")
    public String add(){
        IdWorker idWorker = new IdWorker(1L);
        Employee employee = new Employee();
        employee.setId(idWorker.nextId().toString());
        employee.setName("");
        employee.setAge(26);

        employeeService.save(employee);

        return "success";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(@PathVariable String id){
        employeeService.delete(id);
        return "success";
    }

    //局部更新
    @RequestMapping("/update")
    public String update(){
        Employee employee = employeeService.queryEmployeeById("1");
        employee.setName("哈哈");
        employeeService.save(employee);
        return "success";
    }

    //查询
    @RequestMapping("/query/{id}")
    public Employee query(@PathVariable("id")String id){

        Employee accountInfo = employeeService.queryEmployeeById(id);

        return accountInfo;
    }

}