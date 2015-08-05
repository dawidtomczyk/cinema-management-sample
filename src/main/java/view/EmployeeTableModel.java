package view;

import model.Employee;
import model.Gender;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 2015-08-01.
 */
public class EmployeeTableModel extends AbstractTableModel {

    private List<Employee> employeeList;
    private String[] colNames = {"ID","First name", "Last name", "Gender", "Age", "Telephone", "Email", "Salary", "Country", "City", "Postal Code"};

    public EmployeeTableModel() {
        employeeList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return employeeList.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employeeList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return employee.getId();
            case 1:
                return employee.getFirstName();
            case 2:
                return employee.getLastName();
            case 3:
                return employee.getGender();
            case 4:
                return employee.getAge();
            case 5:
                return employee.getTelephoneNumber();
            case 6:
                return employee.getEmail();
            case 7:
                return employee.getSalary();
            case 8:
                return employee.getAdress().getCountry();
            case 9:
                return employee.getAdress().getCity();
            case 10:
                return employee.getAdress().getPostalCode();
            default:
                return null;
        }
    }


    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

}
