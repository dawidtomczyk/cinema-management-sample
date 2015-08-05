package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Dawid on 2015-08-04.
 */
public class AgeCatColumnRenderer extends JCheckBox implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setSelected((boolean)value);
        return this;
    }
}
