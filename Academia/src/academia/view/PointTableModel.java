/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.view;

import academia.model.Point;
import academia.model.Student;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jonas
 */
public class PointTableModel extends AbstractTableModel {
    //colunas Dia ; entrada ; saida ; tempo total

    private final int COLUMN_NUMBER = 5;
    private final int COLUMN_STUDENT = 0;
    private final int COLUMN_DAY = 1;
    private final int COLUMN_ENTRY = 2;
    private final int COLUMN_EXIT = 3;
    private final int COLUMN_TIME = 4;
    private List<Point> points;
    private List<Student> students;
    
    public PointTableModel() {
        points = new ArrayList();
        students = new ArrayList();
    }
    
    public PointTableModel(List<Student> students, List<Point> points) {
        this.points = points;
        this.students = students;
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
        if (columnIndex == COLUMN_STUDENT) {
            return "Aluno";
        } else if (columnIndex == COLUMN_DAY) {
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
        if (columnIndex == COLUMN_STUDENT) {
            return String.class;
        } else if (columnIndex == COLUMN_DAY) {
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
        if (columnIndex == COLUMN_STUDENT) {
            return students.get(rowIndex).getName();
        } else if (columnIndex == COLUMN_DAY) {
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
    
    public Student getStudent(int rowIndex) {
        
        return students.get(rowIndex);
    }
    
    public void addRow(Student student, Point point) {
        students.add(student);
        points.add(point);
        order();
        fireTableDataChanged();
    }
    
    public void eraseRow(int rowIndex) {
        points.remove(rowIndex);
        students.remove(rowIndex);
        order();
        fireTableDataChanged();
    }
    
    public void refresh(List<Student> students, List<Point> points) {
        this.students = students;
        this.points = points;
        order();
        fireTableDataChanged();
    }
    
    private void order() {
        List<Student> studentTemp = new ArrayList();
        Collections.sort(points);
        Collections.reverse(points);
        
        Student student = null;
        for (int i = 0; i < points.size(); i++) {
            int codeStudent = points.get(i).getCodeStudent();
            for (int j = 0; j < students.size(); j++) {
                if (students.get(j).getCodeStudent() == codeStudent) {
                    student = students.get(j);
                }
            }
            
            studentTemp.add(student);
        }
        
        students = studentTemp;
    }
}//fim da classe StudentPresentTableModel

