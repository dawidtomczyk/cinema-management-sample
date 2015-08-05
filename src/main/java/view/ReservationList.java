package view;

import componentsListeners.ReservationListListener;
import model.Reservation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Dawid on 2015-08-03.
 */
public class ReservationList extends JPanel {

    private JList reservationJList;
    private DefaultListModel reservationListModel;
    private JPopupMenu listMenu;
    private ReservationListListener reservationListListener;

    public ReservationList() {

        setLayout(new BorderLayout());
        Dimension size = getPreferredSize();
        size.width = 200;
        setMinimumSize(size);

        reservationJList = new JList();
        reservationListModel = new DefaultListModel();
        reservationJList.setCellRenderer(new ReservationListRenderer());
        listMenu = new JPopupMenu();

        reservationListModel.addElement("Choose seance from table");
        reservationJList.setModel(reservationListModel);

        JMenuItem removeItem = new JMenuItem("Delete");
        listMenu.add(removeItem);
        reservationJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!listContainsString()) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        reservationJList.setSelectedIndex(reservationJList.locationToIndex(e.getPoint()));
                        listMenu.show(reservationJList, e.getX(), e.getY());
                    }
                }
            }
        });
        removeItem.addActionListener(e -> {
            if(reservationListListener!=null){
                reservationListListener.deleteReservationWhereId(((Reservation)reservationJList.getSelectedValue()).getId());
                reservationListModel.removeElement(reservationJList.getSelectedValue());
            }
        });

        setBorder(BorderFactory.createTitledBorder("Reservations"));
        add(new JScrollPane(reservationJList), BorderLayout.CENTER);
    }

    private boolean listContainsString() {

        for (int i = 0; i < reservationListModel.getSize(); i++) {
            if (reservationListModel.getElementAt(i) instanceof String)
                return true;
        }

        return false;
    }

    public void setReservationList(List<Reservation> reservationList) {
        reservationListModel.removeAllElements();
        if (reservationList.isEmpty())
            reservationListModel.addElement("There is no reservations.");
        else
            reservationList.forEach(i -> {
                reservationListModel.addElement(i);
            });
        reservationJList.setModel(reservationListModel);
    }

    public void setReservationListListener(ReservationListListener reservationListListener) {
        this.reservationListListener = reservationListListener;
    }
}
