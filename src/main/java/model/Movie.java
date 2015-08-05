package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dawid on 2015-07-16.
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "director")
    private String director;

    @Column(name = "duration")
    private int duration;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private MovieCategory movieCategory;

    @Column(name = "only_adult")
    private boolean onlyAdult;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<Seance> movieSeances;


    public Movie() {
    }

    public Movie(String title, String director, int duration, String description,boolean onlyAdult, MovieCategory movieCategory) {
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.description = description;
        this.onlyAdult = onlyAdult;
        this.movieCategory = movieCategory;
    }

    public List<Seance> getMovieSeances() {
        return movieSeances;
    }

    public void setMovieSeances(List<Seance> movieSeances) {
        this.movieSeances = movieSeances;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }

    public boolean isOnlyAdult() {
        return onlyAdult;
    }

    public void setOnlyAdult(boolean onlyAdult) {
        this.onlyAdult = onlyAdult;
    }

    @Override
    public String toString() {
        return title;
    }
}
