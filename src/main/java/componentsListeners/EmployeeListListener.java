package componentsListeners;

import model.Employee;

/**
 * Created by Dawid on 2015-08-01.
 */
public interface EmployeeListListener {

    void deleteEmployee(Long id);
    void showEmployeeInfo(Employee employee);
}
