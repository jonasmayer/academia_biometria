
/*-
 * Classname:             AddPointFrame.java
 *
 * Version information:   (versão)
 *
 * Date:                  15/02/2013 - 17:23:43
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.Point;
import academia.util.DateDocumentFilter;
import academia.util.FontFactory;
import academia.util.JContextPopupMenu;
import academia.util.LogMaker;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class AddPointFrame {

    private JFrame frame;
    private JLabel dateLabel;
    private JLabel entryLabel;
    private JLabel exitLabel;
    private JTextField dateField;
    private JSpinner entryField;
    private JSpinner exitField;
    private JButton okButton;
    private JButton cancelButton;
    private int x;
    private int y;
    private String title;
    private Point point;

    public AddPointFrame() {
        title = "Adicionar Entrada";
        createView();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormated = dateFormat.format(new java.util.Date());
        dateField.setText(dateFormated);
    }

    public AddPointFrame(Point point) {
        title = "Editar Ponto";
        this.point = point;
        createView();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormated = dateFormat.format(point.getDay());
        dateField.setText(dateFormated);
        entryField.setValue(point.getEntry());
        if (point.getExit() != null) {
            exitField.setValue(point.getExit());
        }
    }

    private void createView() {

        frame = new JFrame(title);
        dateLabel = new JLabel("Data :");
        entryLabel = new JLabel("Entrada :");
        exitLabel = new JLabel("Saída :");
        dateField = new JTextField();
        //  entryField = new JTextField();
        // exitField = new JTextField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancelar");

        SpinnerDateModel modelEntry = new SpinnerDateModel();
        modelEntry.setCalendarField(Calendar.MINUTE);
        SpinnerDateModel modelExit = new SpinnerDateModel();
        modelExit.setCalendarField(Calendar.MINUTE);
        entryField = new JSpinner();
        entryField.setModel(modelEntry);
        entryField.setEditor(new JSpinner.DateEditor(entryField, "HH:mm:ss"));
        exitField = new JSpinner();
        exitField.setModel(modelExit);
        exitField.setEditor(new JSpinner.DateEditor(exitField, "HH:mm:ss"));

        DocumentFilter dateDocumentFilter = new DateDocumentFilter();
        ((AbstractDocument) dateField.getDocument()).setDocumentFilter(dateDocumentFilter);


        frame.setLayout(null);
        takeScreenSize();
        frame.setBounds((x - 500) / 2, (y - 350) / 2, 500, 350);
        dateLabel.setBounds(50, 50, 100, 30);
        entryLabel.setBounds(50, 100, 100, 30);
        exitLabel.setBounds(50, 150, 100, 30);
        dateField.setBounds(150, 50, 150, 30);
        entryField.setBounds(150, 100, 150, 30);
        exitField.setBounds(150, 150, 150, 30);
        okButton.setBounds(100, 250, 125, 30);
        cancelButton.setBounds(250, 250, 125, 30);

        dateLabel.setFont(FontFactory.getFontDefault());
        entryLabel.setFont(FontFactory.getFontDefault());
        exitLabel.setFont(FontFactory.getFontDefault());
        dateField.setFont(FontFactory.getFontDefault());
        entryField.setFont(FontFactory.getFontDefault());
        exitField.setFont(FontFactory.getFontDefault());
        okButton.setFont(FontFactory.getFontLarge());
        cancelButton.setFont(FontFactory.getFontLarge());

        dateLabel.setBackground(Color.BLACK);
        entryLabel.setBackground(Color.BLACK);
        exitLabel.setBackground(Color.BLACK);
        dateField.setForeground(Color.BLACK);
        entryField.setForeground(Color.BLACK);
        exitField.setForeground(Color.BLACK);
        okButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);
        frame.getContentPane().setBackground(Color.WHITE);



        frame.add(dateLabel);
        frame.add(entryLabel);
        frame.add(exitLabel);
        frame.add(dateField);
        frame.add(entryField);
        frame.add(exitField);
        frame.add(okButton);
        frame.add(cancelButton);


        JContextPopupMenu contextPopupMenu = new JContextPopupMenu();

        //adiciona evento aos itens do popupmenu
        contextPopupMenu.addInComponet(dateField);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void setActionListenerOkButton(ActionListener actionListener) {
        okButton.addActionListener(actionListener);
    }

    public void setActionListenerCancelButton(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    public void close() {
        frame.dispose();
    }

    public Point getPoint() {
        if (point == null) {
            point = new Point();
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            java.sql.Date day;
            java.util.Date dateEntry = (java.util.Date) entryField.getValue();
            java.sql.Time entry = new java.sql.Time(dateEntry.getTime());
            point.setEntry(entry);

            try {
                if (point != null) {
                    day = new java.sql.Date(dateFormat.parse(dateField.getText()).getTime());
                    point.setDay(day);
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Formato da data incorreto!", "Atenção!", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            java.util.Date dateExit = (java.util.Date) exitField.getValue();
            java.sql.Time exit = new java.sql.Time(dateExit.getTime());
            point.setExit(exit);

            if (point.getEntry().after(point.getExit())) {
                JOptionPane.showMessageDialog(null, "Saida anterior a entrada!", "Atenção!", JOptionPane.WARNING_MESSAGE);

                return null;
            }

        } catch (IllegalArgumentException illegalArgumentException) {
            point = null;
            System.err.println("Erro: formato incorreto!");
            illegalArgumentException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: formato incorreto!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(illegalArgumentException);
        }
        return point;
    }

    public static void main(String args[]) {
        AddPointFrame editPointFrame = new AddPointFrame();
    }

    public void setCloseWindowListener(WindowAdapter windowAdapter) {
        frame.addWindowListener(windowAdapter);
    }
}//fim da classe AddPointFrame

