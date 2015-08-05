package repository;

import model.Reservation;
import model.Seance;

import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public interface SeanceRepository {
    void addSeance(Seance seance);
    List<Seance> load();
    void deleteSeanceWhereId(Long id);


}
