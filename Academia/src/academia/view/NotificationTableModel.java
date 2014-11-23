
/*-
 * Classname:             NotificationTableModel.java
 *
 * Version information:   (versão)
 *
 * Date:                  15/02/2013 - 18:03:04
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class NotificationTableModel extends AbstractTableModel {
    //colunas: nome, entrada

    private final int COLUMN_NUMBER = 2;
    private final int COLUMN_NAME = 0;
    private final int COLUMN_NOTE = 1;
    private List<Student> students;
    private List<String> notes;

    public NotificationTableModel() {
        students = new ArrayList();
        notes = new ArrayList();
    }

    public NotificationTableModel(List<Student> students, List<String> notes) {
        this.students = students;
        this.notes = notes;
        order();


    }

    public int getRowCount() {
        return notes.size();

    }

    public int getColumnCount() {
        return COLUMN_NUMBER;

    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == COLUMN_NAME) {
            return "Nome";
        } else if (columnIndex == COLUMN_NOTE) {
            return "Nota";
        }

        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COLUMN_NAME) {
            return String.class;
        } else if (columnIndex == COLUMN_NOTE) {
            return String.class;
        }

        return String.class;

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        String note = notes.get(rowIndex);
        if (columnIndex == COLUMN_NAME) {
            return student.getName();
        } else if (columnIndex == COLUMN_NOTE) {
            return note;
        }

        return "";

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public String getNoteRow(int rowIndex) {
        return notes.get(rowIndex);
    }

    public void addRow(Student student, String note) {
        students.add(student);
        notes.add(note);
        order();
        fireTableDataChanged();
    }

    public void eraseRow(int rowIndex) {
        students.remove(rowIndex);
        notes.remove(rowIndex);
        order();
        fireTableDataChanged();
    }

    public void order() {
        /*  int j = 0;
         int codeStudentNote[] = new int[notes.size()];
         List<String> tempNotes = notes;
         boolean found = false;
         int newPosition=-1;
         for (int k = 0; k < notes.size(); k++) {
         codeStudentNote[k] = students.get(k).getCodeStudent();
         }
         Collections.sort(students);
         notes.clear();
         for (int i = 0; i < notes.size(); i++) {
         while (!found || j < students.size()) {
         if (codeStudentNote[i] == students.get(j).getCodeStudent()) {
         found = true;
         newPosition = j;
         } else {
         notes.set(newPosition, tempNotes.get(i));
         j++;
         }
         }
         found = false;
         j = 0;
         }*/
    }
}//fim da classe  

