package utils;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Dawid on 2015-07-16.
 */
public class HibernateConfig {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Movie.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Seance.class);
        configuration.addAnnotatedClass(Reservation.class);
        configuration.addAnnotatedClass(MovieCategory.class);


        return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
