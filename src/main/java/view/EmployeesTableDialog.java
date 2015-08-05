package view;

import model.Employee;
import model.Gender;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 2015-08-01.
 */
public class EmployeesTableDialog extends JDialog {

    private JTable employeesTable;
    private JPanel searchPanel;
    private JPanel buttonsPanel;
    private JButton cancelButton;
    private JLabel searchLabel;
    private JTextField searchField;

    private EmployeeTableModel employeesTableModel;
    private List<Employee> employeeList;
    private TableRowSorter<TableModel> tableRowSorter;
    private JPopupMenu employeeTablePopUpMenu;
    private int row;
    private int col;

    public EmployeesTableDialog(JFrame parent) {
        super(parent, "Employees", ModalityType.APPLICATION_MODAL);

        setSize(new Dimension(800, 350));
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        Dimension size = getPreferredSize();
        size.height = 300;
        setPreferredSize(size);

        employeeList = new ArrayList<>();
        //setting up components
        employeesTable = new JTable();
        employeesTable.getTableHeader().setReorderingAllowed(false);
        employeesTable.getTableHeader().setResizingAllowed(false);
        employeesTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        employeesTableModel = new EmployeeTableModel();
        employeesTable.setModel(employeesTableModel);

        tableRowSorter = new TableRowSorter<>();
        tableRowSorter.setModel(employeesTableModel);

        employeesTable.setRowSorter(tableRowSorter);

        //setting renderers and editors



        //setting up components

        buttonsPanel = new JPanel();
        searchPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        searchLabel = new JLabel("Search: ");
        searchField = new JTextField(10);

        cancelButton.addActionListener(e -> {
            setVisible(false);
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if (text.trim().length() == 0) {
                    tableRowSorter.setRowFilter(null);
                } else {
                    tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

        ///seting up popUP menu
        employeeTablePopUpMenu = new JPopupMenu();

        JMenuItem deleteItem = new JMenuItem("Delete");

        employeeTablePopUpMenu.add(deleteItem);


        //setting up panels
        buttonsPanel.setLayout(new BorderLayout());

        Border innerButtonsBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);
        Border outerButtonsBorder = BorderFactory.createLineBorder(Color.black);
        buttonsPanel.setBorder(BorderFactory.createCompoundBorder(outerButtonsBorder, innerButtonsBorder));

        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        buttonsPanel.add(searchPanel, BorderLayout.WEST);
        buttonsPanel.add(cancelButton, BorderLayout.EAST);

        add(new JScrollPane(employeesTable), BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public void setEmployeesList(List<Employee> employeeList) {
        employeesTableModel.setEmployeeList(employeeList);
        this.employeeList = employeeList;
    }
}
