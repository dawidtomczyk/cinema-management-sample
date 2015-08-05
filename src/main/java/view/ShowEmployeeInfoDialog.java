package view;

import model.Employee;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dawid on 2015-08-01.
 */
public class ShowEmployeeInfoDialog extends JDialog {

    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel genderLabel;
    private JLabel ageLabel;
    private JLabel telephoneLabel;
    private JLabel emailLabel;
    private JLabel salaryLabel;
    private JLabel countryLabel;
    private JLabel cityLabel;
    private JLabel postalCodeLabel;

    public ShowEmployeeInfoDialog(JFrame parent, Employee employee){
        super(parent, "Employee information", ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);

        setSize(new Dimension(300, 300));

        firstNameLabel = new JLabel();
        lastNameLabel = new JLabel();
        genderLabel = new JLabel();
        ageLabel = new JLabel();
        telephoneLabel = new JLabel();
        emailLabel = new JLabel();
        salaryLabel = new JLabel();
        countryLabel = new JLabel();
        cityLabel = new JLabel();
        postalCodeLabel = new JLabel();

        setText(employee);
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx=1;
        gc.weighty=1;

        //row
        row(gc,firstNameLabel,0);
        row(gc,lastNameLabel,1);
        row(gc,genderLabel,2);
        row(gc,ageLabel,3);
        row(gc,telephoneLabel,4);
        row(gc,emailLabel,5);
        row(gc,salaryLabel,6);
        row(gc,countryLabel,7);
        row(gc, cityLabel,8);
        row(gc, postalCodeLabel,9);
    }

    private void row(GridBagConstraints gc,JLabel label,int row) {
        gc.gridx=0;
        gc.gridy=row;
        add(label, gc);
    }

    public void setText(Employee emp) {
        firstNameLabel.setText(String.format("First name: %s",emp.getFirstName()));
        lastNameLabel.setText(String.format("Last name: %s",emp.getLastName()));
        genderLabel.setText(String.format("Gender: %s",emp.getGender().getGender()));
        ageLabel.setText(String.format("Age: %s",String.valueOf(emp.getAge())));
        telephoneLabel.setText(String.format("Telephone number: %s",emp.getTelephoneNumber()));
        emailLabel.setText(String.format("Email: %s",emp.getEmail()));
        salaryLabel.setText(String.format("Salary: %s",String.valueOf(emp.getSalary())));
        countryLabel.setText(String.format("Country: %s",emp.getAdress().getCountry()));
        cityLabel.setText(String.format("City: %s",emp.getAdress().getCity()));
        postalCodeLabel.setText(String.format("Postal code: %s", emp.getAdress().getPostalCode()));
    }
}
