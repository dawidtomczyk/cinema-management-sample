package view;

import componentsListeners.MovieCategoryListener;
import model.MovieCategory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dawid on 2015-07-23.
 */
public class MovieCategoryDialog extends JDialog {

    private JLabel categoryLabel;
    private JTextField categoryField;
    private JButton addButton;
    private JButton cancelButton;
    private MovieCategoryListener movieCategoryListener;

    public MovieCategoryDialog(JFrame parent) {
        super(parent, "Movie Category", ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);

        setSize(new Dimension(300, 100));
        categoryLabel = new JLabel("Category: ");
        categoryField = new JTextField(15);
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

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
        add(categoryLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(categoryField, gc);

        //next row
        gc.gridy++;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightInset;
        add(addButton, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 55, 0, 0);
        add(cancelButton, gc);

        cancelButton.addActionListener(e -> setVisible(false));
        addButton.addActionListener(e -> {
            if (movieCategoryListener != null) {
                if (categoryField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(MovieCategoryDialog.this, "Please insert movie category", "No movie category", JOptionPane.CANCEL_OPTION);
                }
                else{
                    movieCategoryListener.addMovieCategory(new MovieCategory(categoryField.getText()));
                    setVisible(false);
                    categoryField.setText("");
                }

            }

        });
    }

    public void setMovieCategoryListener(MovieCategoryListener movieCategoryListener) {
        this.movieCategoryListener = movieCategoryListener;
    }
}
