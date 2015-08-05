package service;

import model.Movie;
import model.MovieCategory;
import repository.MovieRepository;
import repository.MovieRepositoryImpl;

import java.util.List;

/**
 * Created by Dawid on 2015-07-21.
 */
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(){
        movieRepository = new MovieRepositoryImpl();
    }
    @Override
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    @Override
    public List<Movie> load() {

        List<Movie> movieList = movieRepository.load();
        movieList.sort((m1,m2) -> m1.getTitle().compareTo(m2.getTitle()));

        return  movieList;
    }

    @Override
    public Movie load(Long id) {
        return null;
    }

    @Override
    public void addMovieCategory(MovieCategory movieCategory) {
        movieRepository.addMovieCategory(movieCategory);
    }

    @Override
    public List<MovieCategory> loadMovieCategory() {
        return movieRepository.loadMovieCategory();
    }

    @Override
    public List<Movie> loadMovieWhereMovieCatIs(MovieCategory movieCategory) {
       return movieRepository.loadMovieWhereMovieCatIs(movieCategory);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteMovieById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        movieRepository.deleteCategoryById(id);
    }
}
