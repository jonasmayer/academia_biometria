/*- 
 * Classname:             ControlPainel.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  31/07/2013 - 17:04:09 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package academia.view;

import academia.util.FontFactory;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.sql.Time;
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
public class ControlPainel {

    private JFrame frame;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton noteButton;
    private int x;
    private int y;
    private String title="Painel de Controle";
    private ControlPanelTableModel model;
    private JLabel notificationLabel;
    private JButton enableNotificationButton;
    private boolean notification = true;

    public ControlPainel() {
        createView();
    }

    private void createView() {
        frame = new JFrame(title);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        model = new ControlPanelTableModel();

        table = new JTable(model);
        notificationLabel = new JLabel("Notificações:");
        enableNotificationButton = new JButton("Desativar");

        frame.setLayout(null);
        takeScreenSize();
        frame.setBounds(x - 410, y - 620, 410, 580);
        scrollPane.setBounds(10, 10, 380, 480);
        notificationLabel.setBounds(50, 500, 120, 30);
        enableNotificationButton.setBounds(180, 500, 120, 30);

        //define tamanho das colunas da tabela
        TableColumn column;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                int sizeColumnCode = 100;
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }
            if (i == 1) {
                int sizeColumnCode = 280;
                column.setPreferredWidth(sizeColumnCode);
                column.setMaxWidth(sizeColumnCode);
            }

        }
        table.setFont(FontFactory.getFontDefault());
        notificationLabel.setFont(FontFactory.getFontDefault());
        enableNotificationButton.setFont(FontFactory.getFontLarge());

        table.setForeground(Color.BLACK);
        frame.getContentPane().setBackground(Color.WHITE);
        notificationLabel.setForeground(Color.BLACK);
        enableNotificationButton.setForeground(Color.BLACK);

        table.setAutoCreateRowSorter(true);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.setViewportView(table);
        frame.add(scrollPane);
        frame.add(notificationLabel);
        frame.add(enableNotificationButton);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void setActionListenerEnableNotificationButton(ActionListener actionListener) {
        enableNotificationButton.addActionListener(actionListener);
    }

    public static void main(String args[]) {
        ControlPainel controlPainel = new ControlPainel();
    }

    public void addNote(Time time, String note) {
        model.addRow(time, note);
        if (notification) {
            NotificationDialog n = new NotificationDialog(note);
        }
    }

    public void addNote(String note) {
        Time time = new Time(System.currentTimeMillis());
        addNote(time, note);
    }

    public void requestFrameFocus() {
        frame.requestFocus();
    }

    public void setNotificationWatcher(boolean notification) {
        this.notification = notification;
    }

    public boolean getNotificationWatcher() {
        return notification;
    }

    public void setCloseWindowListener(WindowAdapter windowAdapter) {
        frame.addWindowListener(windowAdapter);
    }

    public void close() {
        frame.dispose();
    }
}//fim da classe ControlPainel  

