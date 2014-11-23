/*- 
 * Classname:             NotificationDialog.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  06/08/2013 - 15:31:19 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package academia.view;

import academia.util.FontFactory;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class NotificationDialog
        implements Runnable {

    private JDialog dialog;
    private JProgressBar pb;
    private int progress;
    private Thread thread;

    public NotificationDialog(String message) {
        thread = new Thread(this);
        createView(message);
        thread.start();
    }

    private void createView(String message) {
        System.out.println("-----");
        dialog = new JDialog();
        pb = new JProgressBar();
        JLabel label = new JLabel(message);

        dialog.setLayout(null);
        dialog.setSize(300, 100);
        label.setBounds(2, 10, 298, 30);
        pb.setBounds(10, 50, 260, 20);

        dialog.getContentPane().setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(FontFactory.getFontDefault());

        dialog.add(label);
        dialog.add(pb);



        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    private void close() {
        if (dialog != null) {
            dialog.dispose();
        }
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) {
        NotificationDialog n = new NotificationDialog("teste");
    }

    public void run() {
        int time = (int) (2.5 * 1000);
        for (int i = 0; i < 100; i++) {
            pb.setValue(i);
            try {
                Thread.sleep(time / 100);
            } catch (InterruptedException ex) {
                close();
            }
        }
        close();
    }
}//fim da classe NotificationDialog  

