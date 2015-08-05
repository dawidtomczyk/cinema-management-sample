package view;

import componentsListeners.EmployeeListListener;
import componentsListeners.MovieTreeListener;
import componentsListeners.SeanceTablelistener;
import componentsListeners.ToolBarListener;
import model.Employee;
import model.Movie;
import model.MovieCategory;
import service.*;
import utils.App;
import utils.HibernateConfig;
import utils.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Created by Dawid on 2015-07-16.
 */
public class MainFrame extends JFrame {

    private JTabbedPane tabbedPane;
    private Toolbar toolbar;

    private MovieCategoryDialog movieCategoryDialog;

    private MoviesPanel moviesPanel;
    private EmployeesPanel employeesPanel;
    private SeancePanel seancePanel;
    private ReservationPanel reservationPanel;

    private UpcomingSeancesTable upcomingSeanceTable;
    private EmployeesList employeesList;
    private ShowEmployeeInfoDialog showEmployeeInfoDialog;
    private EmployeesTableDialog employeesTableDialog;

    private JPanel leftSidePanel;
    private JPanel rightSidePanel;

    private JSplitPane horizontalSplit;
    private ReservationList reservationList;
    private MovieTree movieTree;

    private MovieService movieService;
    private SeanceService seanceService;
    private EmployeeService employeeService;
    private ReservationService reservationService;


    public MainFrame() {

        super("Cinema Management");

        //setting up frame
        setMinimumSize(new Dimension(900, 700));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(createJMenuBar());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit application?", "Exit", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    App.session().close();
                    System.exit(0);
                }
            }
        });

        // good looking app with JTatoo
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //setting up components
        tabbedPane = new JTabbedPane();
        toolbar = new Toolbar();

        movieCategoryDialog = new MovieCategoryDialog(this);

        moviesPanel = new MoviesPanel();
        employeesPanel = new EmployeesPanel();
        seancePanel = new SeancePanel();

        leftSidePanel = new JPanel();
        rightSidePanel = new JPanel();
        reservationPanel = new ReservationPanel();
        upcomingSeanceTable = new UpcomingSeancesTable();
        employeesList = new EmployeesList();

        reservationList = new ReservationList();
        movieTree = new MovieTree((JFrame) getParent());
        horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, reservationList, movieTree);

        //instantiated services
        movieService = new MovieServiceImpl();
        seanceService = new SeanceServiceImpl();
        employeeService = new EmployeeServiceImpl();
        reservationService = new ReservationServiceImpl();

        loadStartingData();


        addingComponentsListeners();
        layoutPanels();

    }

    private JMenuBar createJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //setting up file menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        exitMenu.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit application?", "Exit", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                App.session().close();
                System.exit(0);
            }

        });

        JMenuItem openItem = new JMenuItem("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openItem.setIcon(ImageHandler.uploadIcon("/images/Open16.gif"));

        JMenuItem saveAs = new JMenuItem("Save as...");
        saveAs.setIcon(ImageHandler.uploadIcon("/images/SaveAs16.gif"));

        JMenuItem saveAll = new JMenuItem("Save all");
        saveAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveAll.setIcon(ImageHandler.uploadIcon("/images/SaveAll16.gif"));

        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.setIcon(ImageHandler.uploadIcon("/images/Remove16.gif"));

        JMenuItem refreshItem = new JMenuItem("Refresh");
        refreshItem.setIcon(ImageHandler.uploadIcon("/images/Refresh16.gif"));
        refreshItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));


        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(saveAs);
        fileMenu.add(saveAll);
        fileMenu.addSeparator();
        fileMenu.add(deleteItem);
        fileMenu.add(refreshItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenu);

        //setting up manage menu
        JMenu manageMenu = new JMenu("Manage");
        manageMenu.setMnemonic(KeyEvent.VK_M);

        JMenuItem employeesMenuItem = new JMenuItem("Employees");
        employeesMenuItem.setIcon(ImageHandler.uploadIcon("/images/Bean16.gif"));
        employeesMenuItem.addActionListener(e -> {
            employeesTableDialog = new EmployeesTableDialog(MainFrame.this);
            employeesTableDialog.setEmployeesList(employeeService.load());
            employeesTableDialog.setVisible(true);
        });

        JMenu showMenu = new JMenu("Show");

        JCheckBoxMenuItem showMovies = new JCheckBoxMenuItem("Show movies");
        showMovies.setSelected(true);

        JCheckBoxMenuItem showReservations = new JCheckBoxMenuItem("Show reservations");
        showReservations.setSelected(true);

        showMovies.addActionListener(e -> {
            movieTree.setVisible(showMovies.isSelected());
            if (showMovies.isSelected()) {
                horizontalSplit.setDividerLocation(horizontalSplit.getWidth() - movieTree.getMinimumSize().width);
            }
        });
        showReservations.addActionListener(e -> {
            reservationList.setVisible(showReservations.isSelected());
            if (showReservations.isSelected()) {
                horizontalSplit.setDividerLocation(reservationList.getMinimumSize().width);
            }
        });

        showMenu.add(showMovies);
        showMenu.add(showReservations);

        JMenuItem movieItem = new JMenuItem("Movies");
        movieItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        movieItem.setIcon(ImageHandler.uploadIcon("/images/Play16.gif"));

        JMenuItem reservationItem = new JMenuItem("Reservations");
        reservationItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        reservationItem.setIcon(ImageHandler.uploadIcon("/images/ColumnInsertAfter16.gif"));

        JMenuItem seanceItem = new JMenuItem("Seances");
        seanceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        seanceItem.setIcon(ImageHandler.uploadIcon("/images/Movie16.gif"));

        JMenuItem financeItem = new JMenuItem("Finances");
        financeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        financeItem.setIcon(ImageHandler.uploadIcon("/images/ComposeMail16.gif"));


        manageMenu.add(employeesMenuItem);
        manageMenu.add(showMenu);
        manageMenu.addSeparator();
        manageMenu.add(movieItem);
        manageMenu.add(reservationItem);
        manageMenu.add(seanceItem);
        manageMenu.addSeparator();
        manageMenu.add(financeItem);


        //setting up help menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        JMenuItem homeItem = new JMenuItem("Home");
        homeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        homeItem.setIcon(ImageHandler.uploadIcon("/images/Home16.gif"));

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        aboutItem.setIcon(ImageHandler.uploadIcon("/images/About16.gif"));

        helpMenu.add(homeItem);
        helpMenu.add(aboutItem);


        menuBar.add(fileMenu);
        menuBar.add(manageMenu);
        menuBar.add(helpMenu);


        return menuBar;
    }

    private void loadStartingData() {
        moviesPanel.loadMovieCategories(movieService.loadMovieCategory());
        seancePanel.loadMoviesToCombo(movieService.load());
        reservationPanel.setMoviesOnCombo(movieService.load());
        upcomingSeanceTable.setUpcomingSeanceList(seanceService.load());
        employeesList.setEmployeeList(employeeService.load());
        movieTree.buildMovieCatTree(movieService.loadMovieCategory());


    }

    private void addingComponentsListeners() {

        reservationPanel.setReservationListener(reservation -> {
            reservationService.addReservation(reservation);
        });

        toolbar.setToolBarListener(new ToolBarListener() {
            @Override
            public void addMovieCategory() {
                movieCategoryDialog.setVisible(true);
            }
        });
        movieCategoryDialog.setMovieCategoryListener(mc -> {
            movieService.addMovieCategory(mc);
            moviesPanel.addMovieCategory(mc);
            movieTree.addMovieCategory(mc);
        });
        moviesPanel.setMovieListener(movie -> {
            movieService.addMovie(movie);
            seancePanel.addMovieToCombo(movie);
            movieTree.addMovie(movie);
        });
        employeesPanel.setEmployeeListener(employee -> {
            employeeService.addEmployee(employee);
            employeesList.addEmployeeToList(employee);
        });
        employeesList.setEmployeeListListener(new EmployeeListListener() {
            @Override
            public void deleteEmployee(Long id) {
                employeeService.deleteEmployee(id);
                employeesList.setEmployeeList(employeeService.load());
            }

            @Override
            public void showEmployeeInfo(Employee employee) {
                showEmployeeInfoDialog = new ShowEmployeeInfoDialog(MainFrame.this, employee);
                showEmployeeInfoDialog.setVisible(true);
            }
        });
        seancePanel.setSeanceListener(seance -> {
            seanceService.addSeance(seance);
            upcomingSeanceTable.addUpcomingSeance(seance);
            upcomingSeanceTable.updateTable();
            reservationPanel.setMovieOnCombo(seance.getMovie());
        });
        movieTree.setMovieTreeListener(new MovieTreeListener() {
            @Override
            public List<Movie> loadMoviesToTreeCat(MovieCategory movieCategory) {
                return movieService.loadMovieWhereMovieCatIs(movieCategory);
            }

            @Override
            public void deleteMovie(Movie movie) {
                movieService.deleteMovieById(movie.getId());
                seancePanel.removeMovie(movie);
            }

            @Override
            public void deleteCategory(MovieCategory movieCategory) {
                movieService.deleteCategoryById(movieCategory.getId());
                moviesPanel.removeMovieCategory(movieCategory);
                if(movieCategory.getMovie()!=null)
                     seancePanel.removeMovies(movieCategory.getMovie());
            }
        });
        upcomingSeanceTable.setSeanceTablelistener(new SeanceTablelistener() {
            @Override
            public void showReservationForSeanceWhereId(Long id) {
                reservationList.setReservationList(reservationService.loadReservationForSeanceWhereId(id));
            }
            @Override
            public void deleteSeanceWhereId(Long id) {
                seanceService.deleteSeanceWhereId(id);
            }
        });
        reservationList.setReservationListListener(id -> {
            reservationService.deleteReservationWhereId(id);
        });
    }

    private void layoutPanels() {

        tabbedPane.addTab("Add Seance", ImageHandler.uploadIcon("/images/Play16.gif"), seancePanel);
        tabbedPane.addTab("Add Reservation", ImageHandler.uploadIcon("/images/Bean16.gif"), reservationPanel);
        tabbedPane.addTab("Add Movie", ImageHandler.uploadIcon("/images/Movie16.gif"), moviesPanel);
        tabbedPane.addTab("Add Employee", ImageHandler.uploadIcon("/images/Server16.gif"), employeesPanel);

        //setting up left side panel
        leftSidePanel.setLayout(new BorderLayout());
        leftSidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftSidePanel.setPreferredSize(new Dimension(300, leftSidePanel.getPreferredSize().height));
        leftSidePanel.add(tabbedPane, BorderLayout.CENTER);
        leftSidePanel.add(employeesList, BorderLayout.SOUTH);


        //setting up right side panel
        rightSidePanel.setLayout(new BorderLayout());
        rightSidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightSidePanel.add(upcomingSeanceTable, BorderLayout.CENTER);
        rightSidePanel.add(horizontalSplit, BorderLayout.SOUTH);
        horizontalSplit.setPreferredSize(new Dimension(rightSidePanel.getPreferredSize().width, 250));


        add(toolbar, BorderLayout.NORTH);
        add(leftSidePanel, BorderLayout.WEST);
        add(rightSidePanel, BorderLayout.CENTER);
    }

}
