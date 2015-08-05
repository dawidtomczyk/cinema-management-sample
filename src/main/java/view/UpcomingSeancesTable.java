package view;

import componentsListeners.SeanceTablelistener;
import model.AgeCategory;
import model.Seance;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Dawid on 2015-07-23.
 */
public class UpcomingSeancesTable extends JPanel {

    private JTable seanceTable;
    private UpcomingSeancesTableModel upcomingSeanceTableModel;
    private JPopupMenu popupMenu;
    private SeanceTablelistener seanceTablelistener;

    public UpcomingSeancesTable() {

        //setting up components
        seanceTable = new JTable();
        seanceTable.getTableHeader().setReorderingAllowed(false);
        seanceTable.getTableHeader().setResizingAllowed(false);
        seanceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        popupMenu = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("Delete");
        JMenuItem showReservations = new JMenuItem("Show reservations");

        popupMenu.add(removeItem);
        popupMenu.add(showReservations);

        upcomingSeanceTableModel = new UpcomingSeancesTableModel();
        seanceTable.setModel(upcomingSeanceTableModel);

        seanceTable.setDefaultRenderer(AgeCategory.class, new AgeCatColumnRenderer());


        seanceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = seanceTable.rowAtPoint(e.getPoint());
                seanceTable.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(seanceTable, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(e -> {
            int row = seanceTable.getSelectedRow();
            System.out.println(row);
            if(seanceTablelistener!=null){
                seanceTablelistener.deleteSeanceWhereId((Long) seanceTable.getValueAt(seanceTable.getSelectedRow(),0));
                upcomingSeanceTableModel.fireTableRowsDeleted(row, row);
                upcomingSeanceTableModel.getUpcomingSeanceList().remove(row);
            }
        });
        showReservations.addActionListener(e -> {
            if (seanceTablelistener != null) {
                seanceTablelistener.showReservationForSeanceWhereId((Long) upcomingSeanceTableModel.getValueAt(seanceTable.getSelectedRow(), 0));
            }

        });

        Border titleBorder = BorderFactory.createTitledBorder("Upcoming seances");
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 8, 0);
        setBorder(BorderFactory.createCompoundBorder(emptyBorder, titleBorder));

        setLayout(new BorderLayout());
        add(new JScrollPane(seanceTable), BorderLayout.CENTER);
    }

    public void setUpcomingSeanceList(List<Seance> upcomingSeanceList) {
        upcomingSeanceList.sort((s1, s2) -> s1.getDateTime().compareTo(s2.getDateTime()));
        upcomingSeanceTableModel.setUpcomingSeanceList(upcomingSeanceList);
    }

    public void updateTable() {
        upcomingSeanceTableModel.fireTableDataChanged();
    }

    public void addUpcomingSeance(Seance seance) {

        upcomingSeanceTableModel.getUpcomingSeanceList().add(seance);
        upcomingSeanceTableModel.getUpcomingSeanceList().sort((s1, s2) -> s1.getDateTime().compareTo(s2.getDateTime()));
    }

    public void setSeanceTablelistener(SeanceTablelistener seanceTablelistener) {
        this.seanceTablelistener = seanceTablelistener;
    }
}
