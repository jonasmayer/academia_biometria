
/*-
 * Classname:             StudentPresentTableModel.java
 *
 * Version information:   (versão)
 *
 * Date:                  14/02/2013 - 17:57:25
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.Point;
import academia.model.Student;
import java.sql.Time;
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
public class StudentPresentTableModel extends AbstractTableModel {
    //colunas: nome, entrada

    private final int COLUMN_NUMBER = 2;
    private final int COLUMN_NAME = 0;
    private final int COLUMN_ENTRY = 1;
    private List<Student> students;
    private List<Point> points;

    public StudentPresentTableModel() {
        students = new ArrayList();
        points = new ArrayList();
    }

    public StudentPresentTableModel(List<Point> points, List<Student> students) {
        this.students = students;
        this.points = points;
        order();


    }

    public int getRowCount() {
        return points.size();

    }

    public int getColumnCount() {
        return COLUMN_NUMBER;

    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == COLUMN_NAME) {
            return "Nome";
        } else if (columnIndex == COLUMN_ENTRY) {
            return "Entrada";
        }

        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COLUMN_NAME) {
            return String.class;
        } else if (columnIndex == COLUMN_ENTRY) {
            return Time.class;
        }

        return String.class;

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Point point = points.get(rowIndex);
        Student student = students.get(rowIndex);
        if (columnIndex == COLUMN_NAME) {
            return student.getName();
        } else if (columnIndex == COLUMN_ENTRY) {
            return point.getEntry();
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

    public void addRow(Point point, Student student) {
        students.add(student);
        points.add(point);
        order();
        fireTableDataChanged();
    }

    public void eraseRow(int rowIndex) {
        students.remove(rowIndex);
        points.remove(rowIndex);
        order();
        fireTableDataChanged();
    }

    public void order() {
        int j = 0;
        boolean found = false;
        List<Student> tempStudents = students;
        Collections.sort(points);
        for (int i = 0; i < points.size(); i++) {
            int codeStudent = points.get(i).getCodeStudent();

            while (j < tempStudents.size() || !found) {
                if (codeStudent == tempStudents.get(j).getCodeStudent()) {
                    students.set(i, tempStudents.get(j));
                    found = true;

                }
                j++;
            }
            found = false;
            j = 0;
        }
    }

    void refresh(List<Point> points, List<Student> students) {
        this.points = points;
        this.students = students;

        order();
        fireTableDataChanged();
    }
}//fim da classe StudentPresentTableModel

