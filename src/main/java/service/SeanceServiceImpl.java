package service;

import model.Reservation;
import model.Seance;
import repository.SeanceRepository;
import repository.SeanceRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Dawid on 2015-07-22.
 */
public class SeanceServiceImpl implements SeanceService {

    private SeanceRepository seanceRepository;

    public SeanceServiceImpl(){
        seanceRepository = new SeanceRepositoryImpl();
    }
    @Override
    public void addSeance(Seance seance) {
        seanceRepository.addSeance(seance);
    }

    @Override
    public List<Seance> load() {
        return seanceRepository.load();
    }

    @Override
    public void deleteSeanceWhereId(Long id) {
        seanceRepository.deleteSeanceWhereId(id);
    }
}
