package utils;

import org.hibernate.Session;
import utils.HibernateConfig;
import view.MainFrame;

import javax.swing.*;

/**
 * Created by Dawid on 2015-07-16.
 */
public class App {

    private static Session session = HibernateConfig.getSessionFactory().openSession();

    public static void main(String[] args)
    {
        HibernateConfig.getSessionFactory().openSession();
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));

    }
    public static Session session (){
        return session;
    }


}
