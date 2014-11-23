/*- 
 * Classname:             ContolPanelTableModel.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  31/07/2013 - 18:24:12 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package academia.view;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class ControlPanelTableModel extends AbstractTableModel {
    //colunas hora : mensagem

    private final int COLUMN_NUMBER = 2;
    private final int COLUMN_HOUR = 0;
    private final int COLUMN_NOTE = 1;
    private List<Time> hours;
    private List<String> notes;

    public ControlPanelTableModel() {
        hours = new ArrayList();
        notes = new ArrayList();
    }

    public int getRowCount() {
        return hours.size();
    }

    public int getColumnCount() {
        return COLUMN_NUMBER;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == COLUMN_HOUR) {
            return "Hora";
        } else if (columnIndex == COLUMN_NOTE) {
            return "Nota";
        }
        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COLUMN_HOUR) {
            return String.class;
        } else if (columnIndex == COLUMN_NOTE) {
            return String.class;
        }
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        rowIndex=hours.size()-rowIndex-1;
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        if (columnIndex == COLUMN_HOUR) {

            return timeFormat.format(hours.get(rowIndex));
        } else if (columnIndex == COLUMN_NOTE) {
            return notes.get(rowIndex);
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addRow(Time hour, String note) {
        hours.add(hour);
        notes.add(note);
        fireTableDataChanged();
    }
}//fim da classe ContolPanelTableModel  

