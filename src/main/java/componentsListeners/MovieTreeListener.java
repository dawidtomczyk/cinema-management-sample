package componentsListeners;

import model.Movie;
import model.MovieCategory;

import java.util.List;

/**
 * Created by Dawid on 2015-08-03.
 */
public interface MovieTreeListener {

    List<Movie> loadMoviesToTreeCat(MovieCategory movieCategory);
    void deleteMovie(Movie movie);
    void deleteCategory(MovieCategory movieCategory);
}
