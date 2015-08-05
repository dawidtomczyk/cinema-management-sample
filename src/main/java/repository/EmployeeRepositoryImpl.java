package repository;

import model.Employee;
import org.hibernate.Query;
import org.hibernate.Transaction;
import utils.App;


import java.util.List;

/**
 * Created by Dawid on 2015-07-19.
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {


    @Override
    public void addEmployee(Employee employee) {
        Transaction transaction = App.session().beginTransaction();
        App.session().save(employee);
        transaction.commit();
    }

    @Override
    public List<Employee> load() {
        Transaction transaction = App.session().beginTransaction();
        List<Employee> employeeList = App.session().createCriteria(Employee.class).list();
        transaction.commit();
        return employeeList;
    }

    @Override
    public Employee load(Long id) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        Transaction transaction = App.session().beginTransaction();
        Query query = App.session().createQuery("delete from Employee where id= :id");
        query.setParameter("id",id);
        query.executeUpdate();
        transaction.commit();
    }
}
