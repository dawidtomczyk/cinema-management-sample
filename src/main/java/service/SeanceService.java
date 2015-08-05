package service;

import model.Reservation;
import model.Seance;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public interface SeanceService {
    void addSeance(Seance seance);
    List<Seance> load();
    void deleteSeanceWhereId(Long id);

}
