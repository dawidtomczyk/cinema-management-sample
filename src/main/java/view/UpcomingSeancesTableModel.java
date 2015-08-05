package view;

import model.Seance;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawid on 2015-07-23.
 */
public class UpcomingSeancesTableModel extends AbstractTableModel {
    private String[] colNames = {"ID","Movie","Date","Time","Adult Only","Info"};
    private List<Seance> upcomingSeanceList;

    public UpcomingSeancesTableModel(){
        upcomingSeanceList = new ArrayList<>();
    }


    @Override
    public int getRowCount() {
        return upcomingSeanceList.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Seance seance = upcomingSeanceList.get(rowIndex);

        switch (columnIndex){
            case 0:
                return seance.getId();
            case 1:
                return seance.getMovie().getTitle();
            case 2:
                return seance.getDateTime().getDayOfMonth()+" "+seance.getDateTime().getMonth()+" "+seance.getDateTime().getYear();
            case 3:
                return DateTimeFormatter.ofPattern("HH:mm").format(seance.getDateTime());
            case 4:
                return seance.isOnlyAdult();
            case 5:
                return seance.getAdditionalInfo();

            default:
                return null;
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 4: return Boolean.class;
            default: return  String.class;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    public void setUpcomingSeanceList(List<Seance> upcomingSeanceList){
        this.upcomingSeanceList = upcomingSeanceList;
    }

    public List<Seance> getUpcomingSeanceList() {
        return upcomingSeanceList;
    }
}
