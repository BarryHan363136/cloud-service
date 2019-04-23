package com.barry.cloud.platform.es.service.impl;

import com.barry.cloud.platform.es.dao.EmployeeRepository;
import com.barry.cloud.platform.es.entity.Employee;
import com.barry.cloud.platform.es.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public void delete(String id) {
        employeeRepository.delete(new Employee(id));
    }
}
