package view;

import model.Movie;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 2015-07-16.
 */
public class MovieTableModel extends AbstractTableModel {

    private String[] colNames = new String[]{"Title","Director","Duration","Description","Category","Age category"};
    private List<Movie> movieList;

    public MovieTableModel() {
        movieList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return movieList.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Movie movie = movieList.get(rowIndex);

        switch (columnIndex){
            case 0:
                return movie.getTitle();
            case 1:
                return movie.getDirector();
            case 2:
                return movie.getDuration();
            case 3:
                return movie.getDescription();
            case 4:
                return movie.getMovieCategory();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
