
/*-
 * Classname:             StudentTableModel.java
 *
 * Version information:   (versão)
 *
 * Date:                  13/02/2013 - 17:59:06
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.Student;
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
public class StudentTableModel extends AbstractTableModel {
    //colunas: nome, telefone ,celular

    private final int COLUMN_NUMBER = 3;
    private final int COLUMN_NAME = 0;
    private final int COLUMN_PHONE = 1;
    private final int COLUMN_CELLPHONE = 2;
    private List<Student> students;

    public StudentTableModel() {
        students = new ArrayList();
    }

    public StudentTableModel(List<Student> students) {
        this.students = students;
        Collections.sort(this.students);

    }

    public int getRowCount() {
        return students.size();

    }

    public int getColumnCount() {
        return COLUMN_NUMBER;

    }

    @Override
    public String getColumnName(int columnIndex) {
        if (columnIndex == COLUMN_NAME) {
            return "Nome";
        } else if (columnIndex == COLUMN_PHONE) {
            return "Telefone";
        } else if (columnIndex == COLUMN_CELLPHONE) {
            return "Celular";
        }
        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COLUMN_NAME) {
            return String.class;
        } else if (columnIndex == COLUMN_PHONE) {
            return String.class;
        } else if (columnIndex == COLUMN_CELLPHONE) {
            return String.class;
        }
        return String.class;

    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        if (columnIndex == COLUMN_NAME) {
            return student.getName();
        } else if (columnIndex == COLUMN_PHONE) {
            return student.getPhone();
        } else if (columnIndex == COLUMN_CELLPHONE) {
            return student.getCellPhone();
        }
        return "";

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Student getRow(int rowIndex) {
        return students.get(rowIndex);
    }

    public void addRow(Student student) {
        students.add(student);
        Collections.sort(students);
        fireTableDataChanged();
    }

    public void eraseRow(int rowIndex) {
        students.remove(rowIndex);
        Collections.sort(students);
        fireTableDataChanged();
    }

    public void refresh(List<Student> students) {

        this.students = students;
        Collections.sort(this.students);
        fireTableDataChanged();
    }
}//fim da classe StudentTableModel

