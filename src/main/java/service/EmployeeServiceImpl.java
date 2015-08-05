package service;

import model.Employee;
import org.hibernate.Transaction;
import repository.EmployeeRepository;
import repository.EmployeeRepositoryImpl;
import utils.App;

import java.util.List;

/**
 * Created by Dawid on 2015-07-21.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(){
        employeeRepository = new EmployeeRepositoryImpl();
    }

    @Override
    public void addEmployee(Employee employee) {
            employeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> load() {

        List<Employee> employeeList = employeeRepository.load();
        employeeList.sort((e1,e2) -> e1.getFirstName().compareToIgnoreCase(e2.getFirstName()));
        return employeeList;
    }

    @Override
    public Employee load(Long id) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployee(id);
    }
}
