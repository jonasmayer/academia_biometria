/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.view;

import academia.model.Point;
import academia.model.Student;
import academia.util.FontFactory;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Jonas
 */
public class PointFrame {

    private JFrame frame;
    private JScrollPane scrollPane;
    private JTable table;
    private JLabel titleLabel;
    private JButton addButton;
    private JButton editButton;
    private JButton eraseButton;
    private int x;
    private int y;
    private String title;
    private PointTableModel model;
    private List<Point> points;
    private List<Student> students;

    public PointFrame() {
        createView();
    }

    public PointFrame(List<Student> students, List<Point> points) {
        this.points = points;
        this.students = students;
        title = "Pontos";
        createView();

    }

    private void createView() {
        frame = new JFrame(title);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        if (points != null) {
            model = new PointTableModel(students, points);
        } else {
            model = new PointTableModel();
        }
        table = new JTable(model);
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        eraseButton = new JButton("Excluir");
        titleLabel = new JLabel("Pontos");

        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        frame.setLayout(null);
        takeScreenSize();
        frame.setBounds((x - (x - 450)) / 2, (y - 600) / 2, x - 450, 600);
        scrollPane.setBounds(10, 40, x - 660, 500);
        titleLabel.setBounds((x - 450 - 200) / 2, 10, 200, 30);
        addButton.setBounds(x - 630, 100, 150, 30);
        editButton.setBounds(x - 630, 150, 150, 30);
        eraseButton.setBounds(x - 630, 200, 150, 30);

        table.setFont(FontFactory.getFontDefault());
        titleLabel.setFont(FontFactory.getFontLarge());
        addButton.setFont(FontFactory.getFontLarge());
        editButton.setFont(FontFactory.getFontLarge());
        eraseButton.setFont(FontFactory.getFontLarge());
        titleLabel.setForeground(Color.BLACK);
        addButton.setForeground(Color.BLACK);
        editButton.setForeground(Color.BLACK);
        eraseButton.setForeground(Color.BLACK);
        table.setForeground(Color.BLACK);
        frame.getContentPane().setBackground(Color.WHITE);
        table.setAutoCreateRowSorter(true);

        //define tamanho das colunas da tabela
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                int sizeColumnCode = (x - 670) / 3;
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }
            if (i == 1) {
                int sizeColumnCode = (int) ((x - 670) / 4.5);
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }

        }

        scrollPane.setViewportView(table);
        frame.add(scrollPane);
        frame.add(titleLabel);
        // frame.add(addButton);
        frame.add(editButton);
        frame.add(eraseButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void setActionListenerAddButton(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void setActionListenerEditButton(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void setActionListenerEraseButton(ActionListener actionListener) {
        eraseButton.addActionListener(actionListener);
    }

    public static void main(String args[]) {
        new PointFrame();
    }

    public Point getRow() {
        if (table.getSelectedRow() >= 0) {
            int rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
            return model.getRow(rowIndex);
        } else {
            return null;
        }
    }

    public int getRowIndex() {
        if (table.getSelectedRow() >= 0) {
            return table.convertRowIndexToModel(table.getSelectedRow());
        } else {
            return -1;
        }

    }

    public void setEnable(boolean active) {
        frame.setEnabled(active);
    }

    public void refresh(List<Student> students, List<Point> points) {
        model.refresh(students, points);
        table.repaint();
    }

    public void remove(int row) {
        model.eraseRow(row);
        table.repaint();
    }

    public void requestFrameFocus() {
        frame.requestFocus();
    }

    public void setCloseWindowListener(WindowAdapter windowAdapter) {
        frame.addWindowListener(windowAdapter);
    }

    public void close() {
        frame.dispose();
    }
}//fim da classe  

