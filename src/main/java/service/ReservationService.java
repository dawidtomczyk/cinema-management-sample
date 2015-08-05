package service;

import model.Reservation;

import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public interface ReservationService {

    void addReservation(Reservation reservation);
    List<Reservation> load();
    List<Reservation> loadReservationForSeanceWhereId(Long id);
    void deleteReservationWhereId(Long id);

}
