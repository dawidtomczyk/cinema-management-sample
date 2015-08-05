package view;

import componentsListeners.ToolBarListener;
import utils.ImageHandler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dawid on 2015-07-23.
 */
public class Toolbar extends JToolBar {

    private JButton addMovieCategory;
    private JButton editButton;
    private JButton exportButton;
    private JButton findButton;
    private JButton helpButton;
    private JButton historyButton;
    private JButton jarButton;
    private JButton openButton;
    private JButton applicationButton;


    private ToolBarListener toolBarListener;
    public Toolbar(){

        addMovieCategory = new JButton();
        addMovieCategory.setIcon(ImageHandler.uploadIcon("/images/Add16.gif"));
        addMovieCategory.setToolTipText("Add Movie Category");

        editButton = new JButton();
        editButton.setIcon(ImageHandler.uploadIcon("/images/Edit16.gif"));
        editButton.setToolTipText("Edit");

        exportButton = new JButton();
        exportButton.setIcon(ImageHandler.uploadIcon("/images/Export16.gif"));
        exportButton.setToolTipText("Export...");

        findButton = new JButton();
        findButton.setIcon(ImageHandler.uploadIcon("/images/Find16.gif"));
        findButton.setToolTipText("Find");

        helpButton = new JButton();
        helpButton.setIcon(ImageHandler.uploadIcon("/images/Help16.gif"));
        helpButton.setToolTipText("Help");

        historyButton = new JButton();
        historyButton.setIcon(ImageHandler.uploadIcon("/images/History16.gif"));
        historyButton.setToolTipText("History");

        jarButton = new JButton();
        jarButton.setIcon(ImageHandler.uploadIcon("/images/Jar16.gif"));
        jarButton.setToolTipText("Something");

        openButton = new JButton();
        openButton.setIcon(ImageHandler.uploadIcon("/images/Open16.gif"));
        openButton.setToolTipText("Open");

        applicationButton = new JButton();
        applicationButton.setIcon(ImageHandler.uploadIcon("/images/Application16.gif"));
        applicationButton.setToolTipText("Application");

        addMovieCategory = new JButton();
        addMovieCategory.setIcon(ImageHandler.uploadIcon("/images/Add16.gif"));
        addMovieCategory.setToolTipText("Add Movie Category");

        addMovieCategory.addActionListener(e -> {
            if (toolBarListener != null) {
                toolBarListener.addMovieCategory();
            }
        });

        add(addMovieCategory);
        addSeparator();
        add(applicationButton);
        add(openButton);
        addSeparator();
        add(editButton);
        add(exportButton);
        add(findButton);
        addSeparator();
        add(jarButton);
        add(historyButton);
        add(helpButton);
    }
    public void setToolBarListener(ToolBarListener toolBarListener){
        this.toolBarListener = toolBarListener;
    }

}
