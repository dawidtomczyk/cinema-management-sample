package view;

import model.Movie;
import model.MovieCategory;
import utils.ImageHandler;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by Dawid on 2015-08-03.
 */
public class MovieTreeCellRenderer extends JLabel implements TreeCellRenderer {

    private DefaultTreeCellRenderer nonLeafRenderer;

    public MovieTreeCellRenderer() {
        setOpaque(true);
        nonLeafRenderer = new DefaultTreeCellRenderer();
        nonLeafRenderer.setOpenIcon(ImageHandler.uploadIcon("/images/WebComponentAdd16.gif"));
        nonLeafRenderer.setClosedIcon(ImageHandler.uploadIcon("/images/About16.gif"));

    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {


        if (leaf) {

            Movie movie = (Movie) ((DefaultMutableTreeNode) value).getUserObject();
            this.setText(movie.getTitle());
            this.setIcon(ImageHandler.uploadIcon("/images/Play16.gif"));

            if (selected) {
                this.setForeground(UIManager.getColor("Tree.selectionForeground"));
                this.setBackground(UIManager.getColor("Tree.selectionBackground"));
            } else {
                this.setForeground(UIManager.getColor("Tree.textForeground"));
                this.setBackground(UIManager.getColor("Tree.textBackground"));
            }

            return this;
        } else {
            return nonLeafRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
    }
}
