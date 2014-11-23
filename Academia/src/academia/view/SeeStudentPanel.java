
/*-
 * Classname:             SeeStudentPanel.java
 *
 * Version information:   (versão)
 *
 * Date:                  13/02/2013 - 17:13:01
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.Student;
import academia.util.FontFactory;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class SeeStudentPanel {

    private static JPanel panel;
    private JButton seeButton;
    private JButton addButton;
    private JButton historyButton;
    private JButton editButton;
    private JButton eraseButton;
    private JLabel titlePanel;
    private JScrollPane scrollPane;
    private JTable table;
    private StudentTableModel tableModel;
    private int x;
    private int y;

    public SeeStudentPanel() {
        tableModel = new StudentTableModel();
        createView();
    }

    public SeeStudentPanel(List<Student> students) {
        tableModel = new StudentTableModel(students);
        createView();
    }

    private void createView() {
        takeScreenSize();
        panel = new JPanel();

        seeButton = new JButton("Visualizar");
        addButton = new JButton("Adicionar");
        historyButton = new JButton("Ver Histórico");
        editButton = new JButton("Editar Dados");
        eraseButton = new JButton("Excluir");
        titlePanel = new JLabel("Alunos");
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table = new JTable();

        titlePanel.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.setLayout(null);
        table.setAutoCreateRowSorter(true);
        scrollPane.setHorizontalScrollBar(new JScrollBar(0));
        scrollPane.setVerticalScrollBar(new JScrollBar(0));
        table.setModel(tableModel);

        panel.setBounds(0, 0, x, y);
        titlePanel.setBounds((x - 300) / 2, 10, 300, 30);
        seeButton.setBounds(x - 360, 50, 150, 30);
        addButton.setBounds(x - 360, 100, 150, 30);
        historyButton.setBounds(x - 360, 150, 150, 30);
        editButton.setBounds(x - 360, 200, 150, 30);
        eraseButton.setBounds(x - 360, 250, 150, 30);
        scrollPane.setBounds(50, 50, x - 500, y - 200);
        table.setBounds(0, 0, x - 500, y - 150);

        seeButton.setFont(FontFactory.getFontLarge());
        addButton.setFont(FontFactory.getFontLarge());
        historyButton.setFont(FontFactory.getFontLarge());
        editButton.setFont(FontFactory.getFontLarge());
        eraseButton.setFont(FontFactory.getFontLarge());
        table.setFont(FontFactory.getFontDefault());
        titlePanel.setFont(FontFactory.getFontLarge());

        seeButton.setForeground(Color.BLACK);
        addButton.setForeground(Color.BLACK);
        historyButton.setForeground(Color.BLACK);
        editButton.setForeground(Color.BLACK);
        eraseButton.setForeground(Color.BLACK);
        scrollPane.setForeground(Color.BLACK);
        table.setForeground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        titlePanel.setForeground(Color.BLACK);
        panel.setBackground(Color.white);



        panel.add(titlePanel);
        panel.add(seeButton);
        panel.add(addButton);
        panel.add(historyButton);
        panel.add(editButton);
        panel.add(eraseButton);
        scrollPane.setViewportView(table);
        panel.add(scrollPane);

        panel.setVisible(true);
    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void setActionListenerSeeButton(ActionListener actionListener) {
        seeButton.addActionListener(actionListener);
    }

    public void setActionListenerAddButton(ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void setActionListenerHistoryButton(ActionListener actionListener) {
        historyButton.addActionListener(actionListener);
    }

    public void setActionListenerEditButton(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void setActionListenerEraseButton(ActionListener actionListener) {
        eraseButton.addActionListener(actionListener);
    }

    public Student getRowSelected() {
        if (table.getSelectedRow() >= 0) {
            int row = table.convertRowIndexToModel(table.getSelectedRow());
            return tableModel.getRow(row);
        } else {
            return null;
        }
    }

    public int getRowIndexSelected() {
        if (table.getSelectedRow() >= 0) {
            return table.convertRowIndexToModel(table.getSelectedRow());
        } else {
            return -1;
        }
    }

    public void addStudent(Student student) {
        tableModel.addRow(student);
    }

    public void setEnable(boolean enable) {
        panel.setEnabled(enable);
    }

    public static void main(String args[]) {
        SeeStudentPanel seeStudentPanel = new SeeStudentPanel();
        MainFrame mainFrame = new MainFrame();

        mainFrame.changePanel(panel);

    }//fim do método main

    public JPanel getPanel() {
        return panel;
    }

    public void remove(int row) {
        tableModel.eraseRow(row);
        table.repaint();
    }

    public void refresh(List<Student> students) {
        tableModel.refresh(students);
        table.repaint();
    }

    public void setTitle(String title) {
        titlePanel.setText(title);
        panel.setBackground(new Color(250, 250, 250));
    }
}//fim da classe SeeStudentPanel

