
/*-
 * Classname:             StudentPresentPanel.java
 *
 * Version information:   (versão)
 *
 * Date:                  14/02/2013 - 17:37:39
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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class StudentPresentPanel {

    private static JPanel panel;
    private JButton generateOutputButton;
    private JLabel titlePanel;
    private JScrollPane scrollPane;
    private JTable table;
    private StudentPresentTableModel tableModel;
    private int x;
    private int y;

    public StudentPresentPanel() {
        tableModel = new StudentPresentTableModel();
        createView();
    }

    public StudentPresentPanel(List<Student> students, List<Point> points) {
        tableModel = new StudentPresentTableModel(points, students);
        createView();
    }

    private void createView() {
        takeScreenSize();
        panel = new JPanel();

        generateOutputButton = new JButton("Gerar Saída");
        titlePanel = new JLabel("Alunos Presentes");
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table = new JTable();
        titlePanel.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.setLayout(null);

        scrollPane.setHorizontalScrollBar(new JScrollBar(0));
        scrollPane.setVerticalScrollBar(new JScrollBar(0));
        table.setModel(tableModel);
        panel.setSize(x, y);
        titlePanel.setBounds((x - 150) / 2, 10, 150, 30);
        generateOutputButton.setBounds(x - 350, 100, 150, 30);
        scrollPane.setBounds(50, 50, x - 500, y - 200);
        table.setBounds(0, 0, x - 500, y - 150);

        generateOutputButton.setFont(FontFactory.getFontLarge());
        table.setFont(FontFactory.getFontDefault());
        titlePanel.setFont(FontFactory.getFontLarge());

        generateOutputButton.setForeground(Color.BLACK);
        scrollPane.setForeground(Color.BLACK);
        titlePanel.setForeground(Color.BLACK);
        table.setForeground(Color.BLACK);
        //scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.setBackground(Color.WHITE);

        table.setAutoCreateRowSorter(true);

        panel.add(titlePanel);
        panel.add(generateOutputButton);
        scrollPane.setViewportView(table);
        panel.add(scrollPane);

        panel.setVisible(true);
    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void setActionListenerGenerateOutputButton(ActionListener actionListener) {
        generateOutputButton.addActionListener(actionListener);
    }

    public void update(List<Student> students, List<Point> points) {
        tableModel.refresh(points, students);
        table.repaint();

    }

    public static void main(String args[]) {
        StudentPresentPanel seeStudentPanel = new StudentPresentPanel();
        MainFrame mainFrame = new MainFrame();

        mainFrame.changePanel(panel);

    }//fim do método main

    public Point getSelectedPoint() {

        if (table.getSelectedRow() >= 0) {
            int row = table.convertRowIndexToModel(table.getSelectedRow());
            return tableModel.getRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}//fim da classe StudentPresentPanel

