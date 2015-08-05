package model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Dawid on 2015-07-19.
 */
@Entity
public class Seance {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Movie movie;
    @Column(name="date")
    private LocalDateTime dateTime;
    @Column(name = "only_adult")
    private boolean onlyAdult;
    @Column(name="info")
    private String additionalInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seance")
    private List<Reservation> reservations;

    public Seance() {
    }

    public Seance(Movie movie, LocalDateTime dateTime,String additionalInfo,boolean onlyAdult) {
        this.movie = movie;
        this.dateTime = dateTime;
        this.additionalInfo = additionalInfo;
        this.onlyAdult = onlyAdult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public boolean isOnlyAdult() {
        return onlyAdult;
    }

    public void setOnlyAdult(boolean onlyAdult) {
        this.onlyAdult = onlyAdult;
    }

    public String toString(){
        return movie.getTitle();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


}
