package view;

import componentsListeners.MovieListener;
import model.Movie;
import model.MovieCategory;
import utils.Util;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by Dawid on 2015-07-16.
 */
public class MoviesPanel extends JPanel {

    private JLabel titleLabel;

    private JTextField titleField;
    private JTextField directorField;

    private JCheckBox onlyAdult;

    private JTextArea descriptionArea;

    private JSpinner durationSpinner;

    private JComboBox movieCatCombo;
    private DefaultComboBoxModel movieCatComboModel;

    private JButton addButton;
    private JButton clearButton;

    private StringBuilder errorMessage;
    private MovieListener movieListener;


    public MoviesPanel() {


        //instantiated components
        titleLabel = new JLabel("Title: ");

        onlyAdult = new JCheckBox();
        onlyAdult.setSelected(false);

        titleField = new JTextField(15);
        directorField = new JTextField(15);
        durationSpinner = new JSpinner();
        descriptionArea = new JTextArea(7, 15);

        movieCatCombo = new JComboBox();
        movieCatComboModel = new DefaultComboBoxModel();

        addButton = new JButton("Add");
        clearButton = new JButton("Clear");

        //add buttons listeners
        addButton.addActionListener(e -> {

            if (hasErrors()) {
                JOptionPane.showMessageDialog(MoviesPanel.this.getParent().getParent(), errorMessage, "Invalid input", JOptionPane.ERROR_MESSAGE);
            } else {
                String title = titleField.getText();
                String director = directorField.getText();
                String description = descriptionArea.getText();
                boolean onlyForAdult = onlyAdult.isSelected();
                int duration = (int) durationSpinner.getValue();
                MovieCategory movieCategory = (MovieCategory) movieCatCombo.getSelectedItem();

                if (movieListener != null) {
                    movieListener.addMovie(new Movie(title, director, duration, description, onlyForAdult, movieCategory));
                    clearField();
                    setVisible(false);
                }

            }
        });

        clearButton.addActionListener(e -> {
            clearField();
        });


        //setting components size
        Dimension size = titleField.getPreferredSize();
        movieCatCombo.setPreferredSize(size);
        addButton.setPreferredSize(clearButton.getPreferredSize());
        durationSpinner.setPreferredSize(new Dimension(size.width / 2, size.height));

        //setting up spinner model
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(60, 0, 999, 1);
        durationSpinner.setModel(spinnerNumberModel);

        //laying out addMoviePanel components
        layoutAddMoviePanelComponents();


    }

    public void addMovieCategory(MovieCategory movieCategory) {
        movieCatComboModel.addElement(movieCategory);
        movieCatCombo.setModel(movieCatComboModel);

    }

    public void loadMovieCategories(List<MovieCategory> movieCategoryList) {

        movieCatComboModel.addElement("--Please select");
        movieCategoryList.sort((c1, c2) -> c1.getCategory().compareToIgnoreCase(c2.getCategory()));
        movieCategoryList.forEach(i -> movieCatComboModel.addElement(i));

        movieCatCombo.setModel(movieCatComboModel);

    }


    private void layoutAddMoviePanelComponents() {

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
        add(titleLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(titleField, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Director: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(directorField, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Description: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JScrollPane(descriptionArea), gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Only adult: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(onlyAdult, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Duration: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(durationSpinner, gc);

        //next row
        gc.gridy++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightInset;
        add(new JLabel("Movie Category: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(movieCatCombo, gc);


        //next row
        gc.gridy++;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        add(addButton, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 55, 0, 0);
        add(clearButton, gc);
    }


    private void clearField() {
        titleField.setText("");
        directorField.setText("");
        durationSpinner.setValue(60);
        descriptionArea.setText("");
        movieCatCombo.setSelectedIndex(0);
    }

    private boolean hasErrors() {

        errorMessage = new StringBuilder();

        int i = 0;
        if (!Util.checkInput(titleField.getText(), "\\w{3,25}")) {
            errorMessage.append("Title field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (!Util.checkInput(directorField.getText(), "\\w{3,25}")) {
            errorMessage.append("Directory field must have at least 3 and less than 25!\n \n");
            i++;
        }
        if (movieCatCombo.getSelectedIndex() == 0) {
            errorMessage.append("Please select movie category.\n \n ");
            i++;
        }

        return i > 0;
    }

    public void setMovieListener(MovieListener movieListener) {
        this.movieListener = movieListener;
    }
    public void removeMovieCategory(MovieCategory movieCategory){
        movieCatComboModel.removeElement(movieCategory);
    }
}
