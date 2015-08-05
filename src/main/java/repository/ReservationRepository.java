package repository;

import model.Reservation;

import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public interface ReservationRepository {
    void addReservation(Reservation reservation);

    List<Reservation> loadReservationForSeanceWhereId(Long id);
    void deleteReservationWhereId(Long id);
    List<Reservation> load();
}
