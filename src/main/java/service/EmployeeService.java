package service;

import model.Employee;

import java.util.List;

/**
 * Created by Dawid on 2015-07-21.
 */
public interface EmployeeService {
    void addEmployee(Employee employee);
    List<Employee> load();
    Employee load(Long id);
    void deleteEmployee(Long id);
}
