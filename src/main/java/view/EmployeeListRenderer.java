package view;

import model.Employee;
import utils.ImageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dawid on 2015-08-01.
 */
public class EmployeeListRenderer extends JLabel implements ListCellRenderer {

    public EmployeeListRenderer(){
        this.setIcon(ImageHandler.uploadIcon("/images/Bean16.gif"));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        Employee employee = (Employee)value;
        this.setText(String.format("%s %s",employee.getFirstName(),employee.getLastName()));

        if(isSelected) {
            this.setBackground(Color.black);
        }
        else {
            this.setBackground(list.getBackground());
        }

        return this;
    }
}
