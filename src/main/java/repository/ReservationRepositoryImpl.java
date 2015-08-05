package repository;

import model.Reservation;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.App;

import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public class ReservationRepositoryImpl implements ReservationRepository {
    @Override
    public void addReservation(Reservation reservation) {
        Transaction transaction = App.session().beginTransaction();
        App.session().save(reservation);
        transaction.commit();
    }

    @Override
    public List<Reservation> load() {
        Transaction transaction = App.session().beginTransaction();
        List<Reservation> reservationList = App.session().createCriteria(Reservation.class).list();
        transaction.commit();
        return reservationList;
    }
    @Override
    public List<Reservation> loadReservationForSeanceWhereId(Long id) {
        Transaction transaction = App.session().beginTransaction();
        List<Reservation> reservationList= App.session().createCriteria(Reservation.class).add(Restrictions.eq("seance.id", id)).list();
        transaction.commit();
        return reservationList;
    }

    @Override
    public void deleteReservationWhereId(Long id) {
        Transaction transaction = App.session().beginTransaction();
        Reservation reservation = (Reservation)App.session().load(Reservation.class,id);
        App.session().delete(reservation);
        transaction.commit();
    }
}
