package view;

import model.Reservation;
import utils.ImageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dawid on 2015-08-04.
 */
public class ReservationListRenderer extends JLabel implements ListCellRenderer {
    public ReservationListRenderer() {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setIcon(ImageHandler.uploadIcon("/images/Host16.gif"));
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value instanceof Reservation) {
            Reservation reservation = (Reservation) value;
            this.setText(String.format("%s %s", reservation.getFirstName(), reservation.getLastName()));

            if (isSelected) {
                this.setBackground(Color.black);
            } else {
                this.setBackground(list.getBackground());
            }
            return this;
        }
        return new JLabel(value.toString());
    }
}
