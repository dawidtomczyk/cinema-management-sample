package view;

import componentsListeners.MovieTreeListener;
import model.Movie;
import model.MovieCategory;
import utils.Util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dawid on 2015-08-03.
 */
public class MovieTree extends JPanel {

    private JTree movieTree;
    private DefaultTreeModel movieTreeModel;
    private MovieTreeCellRenderer movieTreeCellRenderer;
    private MovieTreeListener movieTreeListener;

    private JPopupMenu treePopUp;
    private DefaultMutableTreeNode root;
    private Map<DefaultMutableTreeNode, Boolean> wasExpanded;

    public MovieTree(JFrame parent) {
        setLayout(new BorderLayout());
        Dimension size = getPreferredSize();
        size.width = 150;
        setMinimumSize(size);

        setBorder(BorderFactory.createTitledBorder("Movies"));

        movieTree = new JTree();
        movieTree.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        movieTreeCellRenderer = new MovieTreeCellRenderer();
        movieTree.setCellRenderer(movieTreeCellRenderer);
        movieTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        root = new DefaultMutableTreeNode("Categories");
        wasExpanded = new HashMap<>();

        treePopUp = new JPopupMenu();

        JMenuItem deleteItem = new JMenuItem("Delete");

        treePopUp.add(deleteItem);
        movieTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TreePath path = movieTree.getPathForLocation(e.getX(), e.getY());

                if (path == null) return;
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                if (node.getUserObject() instanceof Movie || node.getUserObject() instanceof MovieCategory) {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        treePopUp.show(movieTree, e.getX(), e.getY());
                        movieTree.setSelectionPath(path);
                    }
                }
            }
        });
        deleteItem.addActionListener(e -> {
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) movieTree.getLastSelectedPathComponent();

            if (movieTreeListener != null) {
                if (treeNode.getUserObject() instanceof Movie) {
                    movieTreeListener.deleteMovie(((Movie) treeNode.getUserObject()));
                    movieTreeModel.removeNodeFromParent(treeNode);
                    Util.showConfirmDialog(MovieTree.this.getParent().getParent().getParent(), "Movie has been succesfully deleted.", "Deleting movie...");
                } else if (treeNode.getUserObject() instanceof MovieCategory) {
                    movieTreeListener.deleteCategory(((MovieCategory) treeNode.getUserObject()));
                    movieTreeModel.removeNodeFromParent(treeNode);
                    Util.showConfirmDialog(MovieTree.this.getParent().getParent().getParent(), "Category has been succesfully deleted.", "Deleting category...");
                }

            }
        });

        movieTree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();

                if (node.getChildCount() > 0 || node.isRoot())
                    return;
                if (((DefaultMutableTreeNode) movieTreeModel.getRoot()).isNodeChild(node)) {
                    loadMoviesToCategory(node);
                }
                if (node.getUserObject() instanceof MovieCategory) {
                    if (wasExpanded.containsKey(node))
                        return;
                    wasExpanded.put(node, true);
                }

            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {

            }
        });

        add(new JScrollPane(movieTree), BorderLayout.CENTER);
    }

    private void loadMoviesToCategory(DefaultMutableTreeNode node) {
        movieTreeListener.loadMoviesToTreeCat((MovieCategory) node.getUserObject()).forEach(i -> {
            DefaultMutableTreeNode movie = new DefaultMutableTreeNode(i, false);
            node.add(movie);
            movieTreeModel.nodeStructureChanged(node);
            System.out.println(i);
        });
    }

    public void buildMovieCatTree(List<MovieCategory> movieCategoryList) {

        movieCategoryList.forEach(i -> {
            DefaultMutableTreeNode cat = new DefaultMutableTreeNode(i);
            root.add(cat);
        });
        movieTreeModel = new DefaultTreeModel(root, true);
        movieTree.setModel(movieTreeModel);
    }


    public void setMovieTreeListener(MovieTreeListener movieTreeListener) {
        this.movieTreeListener = movieTreeListener;
    }

    public void addMovieCategory(MovieCategory mc) {
        root.add(new DefaultMutableTreeNode(mc, true));
        movieTreeModel.nodeStructureChanged(root);
    }

    public void addMovie(Movie movie) {
        for (int i = 0; i < movieTreeModel.getChildCount(root); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) movieTreeModel.getChild(root, i);
            if (node.getUserObject() == movie.getMovieCategory()) {
                if (wasExpanded.containsKey(node)) {
                    node.add(new DefaultMutableTreeNode(movie, false));
                    movieTreeModel.nodeStructureChanged(node);
                    return;
                }
            }
        }
    }
}
