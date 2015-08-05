package repository;

import model.Movie;
import model.Reservation;
import model.Seance;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.App;

import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public class SeanceRepositoryImpl implements SeanceRepository {

    @Override
    public void addSeance(Seance seance) {
        Transaction transaction = App.session().beginTransaction();
        App.session().save(seance);
        transaction.commit();
    }

    @Override
    public List<Seance> load() {
        Transaction transaction = App.session().beginTransaction();
        List<Seance> seanceList = App.session().createCriteria(Seance.class).list();
        transaction.commit();
        return seanceList;
    }

    @Override
    public void deleteSeanceWhereId(Long id) {
        Transaction transaction = App.session().beginTransaction();
        Seance seance = (Seance)App.session().load(Seance.class,id);
        App.session().delete(seance);
        transaction.commit();
    }
}
