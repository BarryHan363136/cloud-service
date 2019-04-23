package com.barry.cloud.platform.es.service;

import com.barry.cloud.platform.es.entity.Employee;
import java.util.List;

public interface EmployeeService {

    public void save(Employee employee);

    public void saveList(List<Employee> employeeList);

    Employee queryEmployeeById(String id);

    List<Employee> findEmployeesByDepartment(String department);

    public void delete(String id);

}
