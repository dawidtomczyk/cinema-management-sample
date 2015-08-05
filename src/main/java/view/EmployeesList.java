package view;

import componentsListeners.EmployeeListListener;
import model.Employee;
import utils.Util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Dawid on 2015-08-01.
 */
public class EmployeesList extends JPanel {

    private JList employeesList;
    private DefaultListModel employeeListModel;
    private JPopupMenu popupMenu;
    private EmployeeListListener employeeListListener;

    public EmployeesList() {

        setLayout(new BorderLayout());
       // setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        Dimension size = getPreferredSize();
        size.height = 250;
        setPreferredSize(size);
        employeesList = new JList();
        employeesList.setCellRenderer(new EmployeeListRenderer());

        employeeListModel = new DefaultListModel();

        popUpMenu();
        setBorder(BorderFactory.createTitledBorder("Employees"));
        add(new JScrollPane(employeesList), BorderLayout.CENTER);
    }

    private void popUpMenu() {
        popupMenu = new JPopupMenu();

        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem showInfo = new JMenuItem("Show information");
        popupMenu.add(deleteItem);
        popupMenu.addSeparator();
        popupMenu.add(showInfo);

        employeesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int row = employeesList.locationToIndex(e.getPoint());
                if (e.getButton() == MouseEvent.BUTTON3) {
                    employeesList.setSelectedIndex(row);
                    popupMenu.show(employeesList, e.getX(), e.getY());
                }

            }
        });

        deleteItem.addActionListener(e -> {
            if (employeeListListener != null) {

                employeeListListener.deleteEmployee(((Employee) employeesList.getSelectedValue()).getId());
                Util.showConfirmDialog(EmployeesList.this.getParent().getParent().getParent(),"Employee has been succesfully deleted.","Deleting employee...");
            }
        });
        showInfo.addActionListener(e -> {
            if (employeeListListener != null) {
                employeeListListener.showEmployeeInfo((Employee) employeesList.getSelectedValue());
            }
        });
    }

    public void setEmployeeList(List<Employee> employeeList) {
        employeeListModel.removeAllElements();
        employeeList.forEach(i -> employeeListModel.addElement(i));
        employeesList.setModel(employeeListModel);
    }

    public void addEmployeeToList(Employee employee) {
        employeeListModel.addElement(employee);

    }

    public void setEmployeeListListener(EmployeeListListener employeeListListener) {
        this.employeeListListener = employeeListListener;
    }
}
