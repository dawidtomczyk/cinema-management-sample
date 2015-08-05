package view;

import componentsListeners.EmployeeListener;
import model.Employee;
import model.EmployeeAdress;
import model.Gender;
import utils.Util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dawid on 2015-07-16.
 */
public class EmployeesPanel extends JPanel {


    private JButton addButton;
    private JButton clearButton;

    private JLabel firstNameLabel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField telephoneNumberField;
    private JTextField countryField;
    private JTextField cityField;
    private JTextField postalCodeField;
    private JTextField emailField;
    private JLabel salaryLabel;


    private ButtonGroup genderGroup;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;

    private JSpinner ageSpinner;
    private JSlider salarySlider;

    private StringBuilder errorMessage;
    private EmployeeListener employeeListener;


    public EmployeesPanel() {

        setLayout(new BorderLayout());

        //instantiated components
        firstNameLabel = new JLabel("First name: ");

        //fields
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        telephoneNumberField = new JTextField(15);
        countryField = new JTextField(15);
        cityField = new JTextField(15);
        postalCodeField = new JTextField(15);
        emailField = new JTextField(15);
        salaryLabel = new JLabel("0");

        //radio buttons
        genderGroup = new ButtonGroup();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        femaleRadio.setSelected(true);
        maleRadio.setActionCommand("MALE");
        femaleRadio.setActionCommand("FEMALE");
        //components size
        Dimension size = firstNameField.getPreferredSize();

        //spinner
        ageSpinner = new JSpinner();
        ageSpinner.setPreferredSize(new Dimension(size.width / 2, size.height));
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(18, 0, 99, 1);
        ageSpinner.setModel(spinnerNumberModel);


        //slider,
        salarySlider = new JSlider(JSlider.HORIZONTAL,0,9999,0);
        salarySlider.setPreferredSize(new Dimension(size.width-60,size.height));
        salarySlider.addChangeListener(e -> {
            salaryLabel.setText(String.valueOf(salarySlider.getValue()));
        });

        //buttons
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");


        clearButton.addActionListener(e -> {
            clearInput();
        });

        addButton.addActionListener(e -> {

            if (hasErrors()) {
                JOptionPane.showMessageDialog(EmployeesPanel.this.getParent().getParent().getParent(), errorMessage, "Invalid input", JOptionPane.ERROR_MESSAGE);
            } else {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                Gender gender = Gender.valueOf(genderGroup.getSelection().getActionCommand());
                Integer age = (Integer) ageSpinner.getValue();
                String telephoneNumber = telephoneNumberField.getText();
                String email = emailField.getText();
                Integer salary = salarySlider.getValue();
                String country = countryField.getText();
                String city = cityField.getText();
                String postalCode = postalCodeField.getText();

                if (employeeListener != null) {
                    employeeListener.addEmployee(new Employee(firstName,lastName,gender,age,telephoneNumber,email,salary,new EmployeeAdress(country,city,postalCode)));
                    setVisible(false);
                    clearInput();
                    Util.showConfirmDialog(EmployeesPanel.this.getParent().getParent().getParent(), "Employee has been succesfully added.", "Adding employee...");
                }
            }
        });

        layoutComponents();
    }


    private void layoutComponents() {

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;

        Insets rightInsets = new Insets(0, 0, 0, 5);

        //first row

        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(firstNameLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(firstNameField, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Last name: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lastNameField, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(femaleRadio, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 70, 0, 0);
        add(maleRadio, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(ageSpinner, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Telephone: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(telephoneNumberField, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Email: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(emailField, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Salary: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(salarySlider, gc);

        gc.gridx=1;
        gc.insets = new Insets(0, salarySlider.getPreferredSize().width+5, 0, 0);
        add(salaryLabel,gc);


        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Country: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(countryField, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("City: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(cityField, gc);

        //next row

        gc.gridx = 0;
        gc.gridy++;
        gc.insets = rightInsets;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Postal code: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(postalCodeField, gc);

       // next row
        gc.gridy++;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        add(addButton, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 55, 0, 0);
        add(clearButton, gc);

    }


    public void setEmployeeListener(EmployeeListener employeeListener) {
        this.employeeListener = employeeListener;
    }

    private void clearInput() {
        firstNameField.setText("");
        lastNameField.setText("");
        telephoneNumberField.setText("");
        countryField.setText("");
        cityField.setText("");
        postalCodeField.setText("");
        emailField.setText("");
        ageSpinner.setValue(18);
        salarySlider.setValue(0);
    }

    private boolean hasErrors() {

        errorMessage = new StringBuilder();

        int i = 0;
        if (!Util.checkInput(firstNameField.getText(), "\\w{3,25}")) {
            errorMessage.append("First name field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (!Util.checkInput(lastNameField.getText(), "\\w{3,25}")) {
            errorMessage.append("Last name field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (ageSpinner.getValue().equals(0)) {
            errorMessage.append("Please add age.\n \n ");
            i++;
        }
        if (!Util.checkInput(telephoneNumberField.getText(), "\\w{3,25}")) {
            errorMessage.append("Telephone field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (!Util.checkInput(emailField.getText(), "\\w{3,25}")) {
            errorMessage.append("Email field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (salarySlider.getValue() == 0) {
            errorMessage.append("Please add salary.\n \n ");
            i++;
        }
        if (!Util.checkInput(countryField.getText(), "\\w{3,25}")) {
            errorMessage.append("Country field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (!Util.checkInput(cityField.getText(), "\\w{3,25}")) {
            errorMessage.append("City field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (!Util.checkInput(postalCodeField.getText(), "\\w{3,25}")) {
            errorMessage.append("Posta code field must have at least 3 and less than 25!\n \n");
            i++;
        }


        return i > 0;
    }
}
