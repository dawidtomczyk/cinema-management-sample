package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dawid on 2015-07-23.
 */
@Entity
public class MovieCategory {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "category")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieCategory")
    private List<Movie> movie;

    public MovieCategory() {
    }

    public MovieCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }

    public String toString(){
        return category;
    }
}
