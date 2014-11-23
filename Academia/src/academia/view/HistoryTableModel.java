
/*-
 * Classname:             HistoryTableModel.java
 *
 * Version information:   (versão)
 *
 * Date:                  26/02/2013 - 18:04:21
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.Point;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class HistoryTableModel extends AbstractTableModel {
    //colunas Dia ; entrada ; saida ; tempo total

    private final int COLUMN_NUMBER = 4;
    private final int COLUMN_DAY = 0;
    private final int COLUMN_ENTRY = 1;
    private final int COLUMN_EXIT = 2;
    private final int COLUMN_TIME = 3;
    private List<Point> points;

    public HistoryTableModel() {
        points = new ArrayList();
    }

    public HistoryTableModel(List<Point> points) {
        this.points = points;
        Collections.sort(this.points);
    }

    public int getRowCount() {
        return points.size();
    }

    public int getColumnCount() {
        return COLUMN_NUMBER;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == COLUMN_DAY) {
            return "Dia";
        } else if (columnIndex == COLUMN_ENTRY) {
            return "Entrada";
        } else if (columnIndex == COLUMN_EXIT) {
            return "Saída";
        } else if (columnIndex == COLUMN_TIME) {
            return "Tempo Total";
        }
        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COLUMN_DAY) {
            return Date.class;
        } else if (columnIndex == COLUMN_ENTRY) {
            return String.class;
        } else if (columnIndex == COLUMN_EXIT) {
            return String.class;
        } else if (columnIndex == COLUMN_TIME) {
            return String.class;
        }
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Point point = points.get(rowIndex);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        if (columnIndex == COLUMN_DAY) {
            return point.getDay();
        } else if (columnIndex == COLUMN_ENTRY) {
            String time = timeFormat.format(point.getEntry());
            return time;
        } else if (columnIndex == COLUMN_EXIT) {
            String time;
            if (point.getExit() != null) {
                time = timeFormat.format(point.getExit());
            } else {
                time = " ";
            }
            return time;
        } else if (columnIndex == COLUMN_TIME) {
            String time;
            if (point.getExit() != null) {
                long timeEntry = point.getEntry().getTime();
                long timeExit = point.getExit().getTime();
                long timeTotal = timeExit - timeEntry;

                int tempMinute = (int) (timeTotal / 60000);
                int hour = (tempMinute / 60) - ((tempMinute % 60) / 60);
                int minute = (tempMinute % 60);
                DecimalFormat df = new DecimalFormat("00");

                time = (hour + ":" + df.format(minute));
            } else {
                time = "";
            }

            return time;
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Point getRow(int rowIndex) {

        return points.get(rowIndex);
    }

    public void addRow(Point point) {
        points.add(point);
        Collections.sort(points);
        fireTableDataChanged();
    }

    public void eraseRow(int rowIndex) {
        points.remove(rowIndex);
        Collections.sort(points);
        fireTableDataChanged();
    }

    public void refresh(List<Point> points) {
        this.points = points;
        Collections.sort(this.points);
        fireTableDataChanged();
    }
}//fim da classe StudentPresentTableModel

