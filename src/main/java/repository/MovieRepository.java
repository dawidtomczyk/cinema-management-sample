package repository;

import model.Movie;
import model.MovieCategory;

import java.util.List;

/**
 * Created by Dawid on 2015-07-16.
 */
public interface MovieRepository {

    void addMovie(Movie movie);
    List<Movie> load();
    Movie load(Long id);
    void addMovieCategory(MovieCategory movieCategory);
    List<MovieCategory> loadMovieCategory();
    List<Movie> loadMovieWhereMovieCatIs(MovieCategory movieCategory);
    void deleteMovieById(Long id);
    void deleteCategoryById(Long id);
}
