package view;

import componentsListeners.SeanceListener;
import model.Movie;
import model.Seance;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dawid on 2015-07-19.
 */
public class SeancePanel extends JPanel {

    private JLabel movieLabel;

    private JButton addButton;
    private JButton clearButton;

    private JTextArea additionalInfoArea;

    private JComboBox movieCombo;
    private DefaultComboBoxModel movieComboModel;

    private JDatePicker datePicker;
    private JSpinner timeSpinner;
    private JSpinner.DateEditor timeEditor;


    private StringBuilder errorMessage;
    private SeanceListener seanceListener;

    public SeancePanel() {

        //instantiated components

        movieLabel = new JLabel("Movie: ");
        additionalInfoArea = new JTextArea(6, 18);
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");

        addButton.setPreferredSize(clearButton.getPreferredSize());

        addButton.addActionListener(e -> {
//            if(hasErrors()) {
//                JOptionPane.showMessageDialog(SeancePanel.this.getParent(), errorMessage, "Invalid input", JOptionPane.ERROR_MESSAGE);
//            }
//            else {

            Movie movie = (Movie) movieCombo.getSelectedItem();
            String hourMinutes = timeEditor.getFormat().format(timeSpinner.getValue());
            LocalDateTime localDateTime = LocalDateTime.of(datePicker.getModel().getYear(),
                    datePicker.getModel().getMonth(),
                    datePicker.getModel().getDay(),
                    Integer.parseInt(hourMinutes.substring(0, 2)),
                    Integer.parseInt(hourMinutes.substring(3, 5)));
            String additionalInfo = additionalInfoArea.getText();
            Seance seance = new Seance(movie, localDateTime, additionalInfo, movie.isOnlyAdult());
            if (seanceListener != null) {
                seanceListener.addSeance(seance);
            }


        });

        //setting up comboBox
        movieCombo = new JComboBox();
        movieComboModel = new DefaultComboBoxModel();


        //customizing timeSpinner
        timeSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeEditor.getTextField().setEditable(false);
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());


        //customizing datePicker

        UtilDateModel utilDatemodel = new UtilDateModel(new Date());
        JDatePanelImpl datePanel = new JDatePanelImpl(utilDatemodel);
        datePicker = new JDatePickerImpl(datePanel, new DateFormater());

        //setting up components size
        movieCombo.setPreferredSize(new Dimension(datePanel.getPreferredSize().width, movieCombo.getPreferredSize().height));
        timeSpinner.setPreferredSize(new Dimension(datePanel.getPreferredSize().width, timeSpinner.getPreferredSize().height));
        layoutComponents();

    }

    private boolean hasErrors() {
        errorMessage = new StringBuilder();


        int i = 2;


        return i > 0;
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
        add(movieLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(movieCombo, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Date: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add((Component) datePicker, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Time: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(timeSpinner, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Info: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JScrollPane(additionalInfoArea), gc);

        //next row
        gc.gridy++;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        add(addButton, gc);

        gc.weighty = 0.001;
        gc.weightx = 0.025;
        gc.gridx = 1;
        gc.insets = new Insets(0, 65, 0, 0);
        add(clearButton, gc);


    }

    public void addMovieToCombo(Movie movie) {

        movieComboModel.addElement(movie);
        movieCombo.setModel(movieComboModel);
    }

    public void loadMoviesToCombo(List<Movie> moviesList) {

        movieComboModel.addElement("--Please Select--");
        moviesList.forEach(i -> movieComboModel.addElement(i));

        movieCombo.setModel(movieComboModel);
    }

    public void setSeanceListener(SeanceListener seanceListener) {
        this.seanceListener = seanceListener;
    }
    public void removeMovie(Movie movie){
        movieComboModel.removeElement(movie);
    }
    public void removeMovies(List<Movie> movieList)
    {
        movieList.forEach(i -> {
            movieComboModel.removeElement(i);
        });
    }

}

class DateFormater extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}