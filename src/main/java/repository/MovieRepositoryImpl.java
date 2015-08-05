package repository;

import model.Movie;
import model.MovieCategory;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.App;

import java.util.List;

/**
 * Created by Dawid on 2015-07-16.
 */
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public void addMovie(Movie movie) {

        Transaction transaction = App.session().beginTransaction();
        App.session().save(movie);
        transaction.commit();
    }

    @Override
    public List<Movie> load() {
        Transaction transaction = App.session().beginTransaction();
        List<Movie> movieList = App.session().createCriteria(Movie.class).list();
        transaction.commit();
        return movieList;

    }

    @Override
    public Movie load(Long id) {
        return null;
    }

    @Override
    public void addMovieCategory(MovieCategory movieCategory) {
        Transaction transaction = App.session().beginTransaction();
        App.session().save(movieCategory);
        transaction.commit();
    }

    @Override
    public List<MovieCategory> loadMovieCategory() {
        Transaction transaction = App.session().beginTransaction();
        List<MovieCategory> movieCategories = App.session().createCriteria(MovieCategory.class).list();
        transaction.commit();
        return movieCategories;
    }

    @Override
    public List<Movie> loadMovieWhereMovieCatIs(MovieCategory movieCategory) {
        Transaction transaction = App.session().beginTransaction();
        List<Movie> movieList = App.session().createCriteria(Movie.class).add(Restrictions.eq("movieCategory",movieCategory)).list();
        transaction.commit();
        return movieList;
    }

    @Override
    public void deleteMovieById(Long id) {
        Transaction transaction = App.session().beginTransaction();
        Movie movie = (Movie)App.session().load(Movie.class,id);
        App.session().delete(movie);
        transaction.commit();
    }

    @Override
    public void deleteCategoryById(Long id) {
        Transaction transaction = App.session().beginTransaction();
        MovieCategory movieCategory = (MovieCategory)App.session().load(MovieCategory.class,id);
        App.session().delete(movieCategory);
        transaction.commit();
    }
}
