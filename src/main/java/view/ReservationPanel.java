package view;

import componentsListeners.ReservationListener;
import model.Movie;
import model.Reservation;
import model.Seance;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dawid on 2015-07-21.
 */
public class ReservationPanel extends JPanel {

    private JLabel firstNameLabel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox seanceDatesCombo;
    private JComboBox moviesCombo;
    private DefaultComboBoxModel moviesComboModel;
    private DefaultComboBoxModel seanceDatesModel;

    private JButton addButton;
    private JButton clearButton;

    private ReservationListener reservationListener;

    public ReservationPanel() {

        //instantiated components
        firstNameLabel = new JLabel("First name: ");
        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);

        seanceDatesCombo = new JComboBox();
        moviesCombo = new JComboBox();

        moviesComboModel = new DefaultComboBoxModel();
        seanceDatesModel = new DefaultComboBoxModel();


        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
        addButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
           Seance seance = ((Movie) moviesCombo.getSelectedItem()).getMovieSeances().stream().
                    filter(i -> DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(i.getDateTime()).
                            equals(seanceDatesModel.getSelectedItem())).findAny().get();

            if (reservationListener != null) {
                reservationListener.addReservation(new Reservation(firstName, lastName, seance));
            }
        });

        moviesCombo.addActionListener(e -> {
            seanceDatesModel.removeAllElements();
            if (moviesCombo.getSelectedIndex() == 0) {
                seanceDatesModel.addElement("--Please select movie--");
                seanceDatesCombo.setModel(seanceDatesModel);
                return;
            }
            Movie movie = (Movie) moviesCombo.getSelectedItem();
            movie.getMovieSeances().forEach(i -> {
                seanceDatesModel.addElement(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(i.getDateTime()));//
            });
            seanceDatesCombo.setModel(seanceDatesModel);
        });

        //setting combos size
        seanceDatesCombo.setPreferredSize(new Dimension(firstNameField.getPreferredSize().width, seanceDatesCombo.getPreferredSize().height));
        moviesCombo.setPreferredSize(new Dimension(firstNameField.getPreferredSize().width, moviesCombo.getPreferredSize().height));

        layoutComponents();
    }

    private void layoutComponents() {

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        Insets rightInset = new Insets(0, 0, 0, 5);

        gc.weighty = 1;
        gc.weightx = 1;

        //first row
        gc.gridy = 0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(firstNameLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(firstNameField, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Last name: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(lastNameField, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Movie: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(moviesCombo, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Date: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(seanceDatesCombo, gc);

        //next row
        gc.gridy++;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        add(addButton, gc);

        gc.weightx = 0.025;
        gc.gridx = 1;
        gc.insets = new Insets(0, 60, 0, 0);
        add(clearButton, gc);
    }


    public void setReservationListener(ReservationListener reservationListener) {
        this.reservationListener = reservationListener;
    }

    public void setMoviesOnCombo(List<Movie> movieList) {
        moviesComboModel.addElement("--Please select--");
        seanceDatesModel.addElement("--Please select movie--");
        seanceDatesCombo.setModel(seanceDatesModel);
        movieList.forEach(i -> moviesComboModel.addElement(i));

        moviesCombo.setModel(moviesComboModel);
    }

    public void setMovieOnCombo(Movie movie) {
        if (!isMovieOnCombo(movie)) {
            moviesComboModel.addElement(movie);
            moviesCombo.setModel(moviesComboModel);
        }
    }

    private boolean isMovieOnCombo(Movie movie) {
        for (int i = 0; i < moviesComboModel.getSize(); i++) {
            if (moviesComboModel.getElementAt(i) == movie) {
                return true;
            }
        }
        return false;
    }
}
