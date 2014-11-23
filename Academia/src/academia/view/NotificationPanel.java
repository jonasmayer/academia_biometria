
/*-
 * Classname:             NotificationPanel.java
 *
 * Version information:   (versão)
 *
 * Date:                  15/02/2013 - 17:09:59
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
import javax.swing.table.TableColumn;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class NotificationPanel {

    private static JPanel panel;
    private JButton editButton;
    private JButton eraseButton;
    private JLabel titlePanel;
    private JScrollPane scrollPane;
    private JTable table;
    private NotificationTableModel tableModel;
    private int x;
    private int y;
    private List type;
    private List<Student> students;

    public NotificationPanel() {
        tableModel = new NotificationTableModel();
        createView();
    }

    public NotificationPanel(List<Student> students, List<String> notes, List type) {
        this.students = students;
        this.type = type;
        tableModel = new NotificationTableModel(students, notes);
        createView();
    }

    private void createView() {
        takeScreenSize();
        panel = new JPanel();

        editButton = new JButton("Editar Relatório");
        eraseButton = new JButton("Excluir");
        titlePanel = new JLabel("Notas de Acesso");
        titlePanel.setHorizontalTextPosition(SwingConstants.CENTER);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table = new JTable();

        panel.setLayout(null);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.setHorizontalScrollBar(new JScrollBar(0));
        scrollPane.setVerticalScrollBar(new JScrollBar(0));
        table.setModel(tableModel);
        panel.setSize(x, y);
        titlePanel.setBounds((x - 150) / 2, 10, 150, 30);
        editButton.setBounds(x - 350, 100, 200, 30);
        eraseButton.setBounds(x - 350, 155, 200, 30);

        scrollPane.setBounds(50, 50, x - 500, y - 200);
        table.setBounds(0, 0, x - 500, y - 150);

        editButton.setFont(FontFactory.getFontLarge());
        eraseButton.setFont(FontFactory.getFontLarge());
        table.setFont(FontFactory.getFontDefault());
        titlePanel.setFont(FontFactory.getFontLarge());

        editButton.setForeground(Color.BLACK);
        eraseButton.setForeground(Color.BLACK);
        scrollPane.setForeground(Color.BLACK);
        titlePanel.setForeground(Color.BLACK);
        table.setForeground(Color.BLACK);
        panel.setBackground(Color.WHITE);
        // scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        table.setAutoCreateRowSorter(true);
        //define tamanho das colunas da tabela
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                int sizeColumnCode = (x - 550) / 3;
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }


        }

        panel.add(titlePanel);

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

    public void setEditButtonActionListener(ActionListener actionListener) {
        editButton.addActionListener(actionListener);
    }

    public void setEraseButtonActionListener(ActionListener actionListener) {
        eraseButton.addActionListener(actionListener);
    }

    public int getRow() {
        if (table.getSelectedRow() >= 0) {
            return table.convertRowIndexToModel(table.getSelectedRow());
        } else {
            return -1;
        }
    }

    public Student getStudent() {
        if (table.getSelectedRow() >= 0) {
            return students.get(table.convertRowIndexToModel(table.getSelectedRow()));
        } else {
            return null;
        }
    }

    public static void main(String args[]) {
        NotificationPanel notificationPanel = new NotificationPanel();
        MainFrame mainFrame = new MainFrame();

        mainFrame.changePanel(panel);

    }//fim do método main

    public void remove(int row) {

        tableModel.eraseRow(row);
    }

    public JPanel getPanel() {
        return panel;
    }
}//fim da classe StudentPresentPanel

