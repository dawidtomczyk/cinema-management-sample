package service;

import model.Reservation;
import repository.ReservationRepository;
import repository.ReservationRepositoryImpl;

import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(){
        reservationRepository = new ReservationRepositoryImpl();
    }
    @Override
    public void addReservation(Reservation reservation) {
        reservationRepository.addReservation(reservation);
    }

    @Override
    public List<Reservation> load() {
        return reservationRepository.load();
    }
    @Override
    public List<Reservation> loadReservationForSeanceWhereId(Long id) {
        return reservationRepository.loadReservationForSeanceWhereId(id);
    }

    @Override
    public void deleteReservationWhereId(Long id) {
        reservationRepository.deleteReservationWhereId(id);
    }
}
