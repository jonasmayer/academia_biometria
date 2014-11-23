
/*-
 * Classname:             SettingsFrame.java
 *
 * Version information:   1.0
 *
 * Date:                  25/01/2013 - 18:41:52
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.util.FontFactory;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * Panel para edição de configurações
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class SettingsFrame {

    //panel de configurações
    private JFrame panelSettings;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel labelLocalDatabase;
    private JLabel labelDatabaseUser;
    private JLabel labelDatabasePassword;
    private JTextField fieldLocalDatabase;
    private JTextField fieldDatabaseUser;
    private JPasswordField fieldDatabasePassword;
    private int x;
    private int y;
    private final JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem cutMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem selectAllMenuItem;

    /**
     * Método construtor sem parâmetro
     */
    public SettingsFrame() {
        createView();
    }

    public SettingsFrame(String databaseLocal, String userName, String password) {
        createView();
        fieldLocalDatabase.setText(databaseLocal);
        fieldDatabaseUser.setText(userName);
        fieldDatabasePassword.setText(password);
    }

    private void createView() {
        //inicializa componentes
        panelSettings = new JFrame("Configurações");
        panelSettings.setLayout(null);

        //cria componentes
        labelLocalDatabase = new JLabel("IP da base de dados:");
        labelDatabaseUser = new JLabel("Usuário:");
        labelDatabasePassword = new JLabel("Senha:");
        fieldLocalDatabase = new JTextField();
        fieldDatabaseUser = new JTextField();
        fieldDatabasePassword = new JPasswordField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancelar");

        takeScreenSize();
        //define tamanho e localização de componentes
        panelSettings.setBounds((x - 500) / 2, (y - 500) / 2, 500, 500);
        labelLocalDatabase.setBounds(50, 50, 150, 30);
        labelDatabaseUser.setBounds(50, 100, 150, 30);
        labelDatabasePassword.setBounds(50, 150, 150, 30);
        fieldLocalDatabase.setBounds(200, 50, 150, 30);
        fieldDatabaseUser.setBounds(200, 100, 150, 30);
        fieldDatabasePassword.setBounds(200, 150, 150, 30);
        okButton.setBounds(200, 400, 100, 30);
        cancelButton.setBounds(350, 400, 100, 30);


        //define fonte
        labelLocalDatabase.setFont(FontFactory.getFontDefault());
        labelDatabaseUser.setFont(FontFactory.getFontDefault());
        labelDatabasePassword.setFont(FontFactory.getFontDefault());
        fieldLocalDatabase.setFont(FontFactory.getFontDefault());
        fieldDatabaseUser.setFont(FontFactory.getFontDefault());
        fieldDatabasePassword.setFont(FontFactory.getFontDefault());
        okButton.setFont(FontFactory.getFontDefault());
        cancelButton.setFont(FontFactory.getFontDefault());

        //define cor dos componentes
        panelSettings.setBackground(Color.WHITE);
        labelLocalDatabase.setBackground(Color.WHITE);
        labelDatabaseUser.setBackground(Color.WHITE);
        labelDatabasePassword.setBackground(Color.WHITE);
        fieldLocalDatabase.setForeground(Color.BLACK);
        fieldDatabaseUser.setForeground(Color.BLACK);
        fieldDatabasePassword.setForeground(Color.BLACK);
        fieldLocalDatabase.setBackground(Color.WHITE);
        fieldDatabaseUser.setBackground(Color.WHITE);
        fieldDatabasePassword.setBackground(Color.WHITE);
        okButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);
        panelSettings.getContentPane().setBackground(Color.WHITE);

        //adiciona componentes ao panel
        panelSettings.add(okButton);
        panelSettings.add(cancelButton);
        panelSettings.add(labelLocalDatabase);
        panelSettings.add(labelDatabaseUser);
        panelSettings.add(labelDatabasePassword);
        panelSettings.add(fieldLocalDatabase);
        panelSettings.add(fieldDatabaseUser);
        panelSettings.add(fieldDatabasePassword);



        cutMenuItem = new JMenuItem("Recortar");
        copyMenuItem = new JMenuItem("Copiar");
        pasteMenuItem = new JMenuItem("Colar");
        selectAllMenuItem = new JMenuItem("Selecionar tudo");
        popupMenu.add(cutMenuItem);
        popupMenu.add(copyMenuItem);
        popupMenu.add(pasteMenuItem);
        popupMenu.addSeparator();
        popupMenu.add(selectAllMenuItem);

        ActionListener popupMenuListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == cutMenuItem) {
                    JTextField textField = (JTextField) popupMenu.getInvoker();
                    textField.cut();
                }
                if (source == copyMenuItem) {
                    JTextField textField = (JTextField) popupMenu.getInvoker();
                    textField.copy();
                }
                if (source == pasteMenuItem) {
                    JTextField textField = (JTextField) popupMenu.getInvoker();
                    textField.paste();
                }
                if (source == selectAllMenuItem) {
                    JTextField textField = (JTextField) popupMenu.getInvoker();
                    textField.selectAll();
                }
            }
        };
        cutMenuItem.addActionListener(popupMenuListener);
        copyMenuItem.addActionListener(popupMenuListener);
        pasteMenuItem.addActionListener(popupMenuListener);
        selectAllMenuItem.addActionListener(popupMenuListener);
        fieldLocalDatabase.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });



        //define panel como vicivel
        panelSettings.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        panelSettings.setVisible(true);
    }//fim do construtor

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void close() {
        panelSettings.dispose();
    }//fim doo método getPanel

    public String getIP() {
        return fieldLocalDatabase.getText();

    }

    public String getUserName() {
        return fieldDatabaseUser.getText();
    }

    public String getPassword() {
        char[] password = fieldDatabasePassword.getPassword();
        StringBuilder stringTemp = new StringBuilder();
        stringTemp = stringTemp.append(password);
        return stringTemp.toString();
    }

    public void setOkButtonactionListener(ActionListener actionListener) {
        okButton.addActionListener(actionListener);
    }

    public void setCancelButtonactionListener(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    public void setCloseWindowListener(WindowListener windowListener) {
        panelSettings.addWindowListener(windowListener);
    }

    public static void main(String args[]) {
        SettingsFrame settingsPanel = new SettingsFrame();


    }
}//fim da classe SettingsFrame

