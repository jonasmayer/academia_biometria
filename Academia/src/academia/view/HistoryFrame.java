
/*-
 * Classname:             HistoryFrame.java
 *
 * Version information:   (versão)
 *
 * Date:                  26/02/2013 - 17:28:53
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.Point;
import academia.model.Student;
import academia.util.FontFactory;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class HistoryFrame {

    private JFrame frame;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton addButton;
    private JButton editButton;
    private JButton eraseButton;
    private JLabel titleLabel;
    private int x;
    private int y;
    private String title;
    private HistoryTableModel historyTableModel;
    private List<Point> points;

    public HistoryFrame() {
        title = "Histórico";
        createView();

    }

    public HistoryFrame(Student student, Iterator points) {
        this.points = new ArrayList();
        title = "Histórico de " + student.getName();
        while (points.hasNext()) {
            this.points.add((Point) points.next());
        }

        createView();

    }

    private void createView() {
        frame = new JFrame(title);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        if (points != null) {
            historyTableModel = new HistoryTableModel(points);
        } else {
            historyTableModel = new HistoryTableModel();
        }
        table = new JTable(historyTableModel);
        addButton = new JButton("Adicionar");
        editButton = new JButton("Editar");
        eraseButton = new JButton("Excluir");
        titleLabel = new JLabel(title);

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        frame.setLayout(null);
        takeScreenSize();
        frame.setBounds((x - (x - 400)) / 2, (y - 600) / 2, x - 400, 600);
        titleLabel.setBounds((x - 400 - 200) / 2, 10, 200, 30);
        scrollPane.setBounds(10, 40, x - 700, 500);
        addButton.setBounds(x - 650, 100, 150, 30);
        editButton.setBounds(x - 650, 150, 150, 30);
        eraseButton.setBounds(x - 650, 200, 150, 30);

        table.setFont(FontFactory.getFontDefault());
        titleLabel.setFont(FontFactory.getFontLarge());
        addButton.setFont(FontFactory.getFontLarge());
        editButton.setFont(FontFactory.getFontLarge());
        eraseButton.setFont(FontFactory.getFontLarge());

        addButton.setForeground(Color.BLACK);
        titleLabel.setForeground(Color.BLACK);
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
                int sizeColumnCode = (int) ((x - 710) / 3.5);
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }
        }


        scrollPane.setViewportView(table);
        frame.add(scrollPane);
        frame.add(titleLabel);
        frame.add(addButton);
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
        HistoryFrame historyFrame = new HistoryFrame();
    }

    public Point getRow() {
        if (table.getSelectedRow() >= 0) {
            int rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
            return historyTableModel.getRow(rowIndex);
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

    public void refresh(Iterator iterator) {
        points.clear();
        while (iterator.hasNext()) {
            points.add((Point) iterator.next());
        }
        historyTableModel.refresh(points);
        table.repaint();
    }

    public void remove(int row) {
        historyTableModel.eraseRow(row);
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

