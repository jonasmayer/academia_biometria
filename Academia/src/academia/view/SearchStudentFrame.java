
/*-
 * Classname:             SearchStudentFrame.java
 *
 * Version information:   (versão)
 *
 * Date:                  04/03/2013 - 17:25:43
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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class SearchStudentFrame {

    private int x;
    private int y;
    private JFrame frame;
    private JTextField searchField;
    private JComboBox comboBox;
    private JLabel titleLabel;
    private JButton okButton;
    private JButton cancelButton;
    private final JPopupMenu popupMenu = new JPopupMenu();
    private JMenuItem cutMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem selectAllMenuItem;

    public SearchStudentFrame() {
        createView();
    }

    private void createView() {
        frame = new JFrame();
        searchField = new JTextField();
        comboBox = new JComboBox(new Object[]{"Nome", "RG"});
        titleLabel = new JLabel("Buscar");

        okButton = new JButton("Buscar");
        cancelButton = new JButton("Cancelar");

        frame.setTitle("Buscar");
        frame.setLayout(null);

        takeScreenSize();
        frame.setBounds((x - 500) / 2, (y - 300) / 2, 500, 250);
        searchField.setBounds(200, 80, 200, 30);
        comboBox.setBounds(50, 80, 100, 30);
        okButton.setBounds(100, 160, 150, 30);
        cancelButton.setBounds(300, 160, 150, 30);
        titleLabel.setBounds((500 - 150) / 2, 20, 150, 30);

        titleLabel.setHorizontalAlignment(JTextField.CENTER);

        searchField.setFont(FontFactory.getFontDefault());
        comboBox.setFont(FontFactory.getFontDefault());
        okButton.setFont(FontFactory.getFontLarge());
        cancelButton.setFont(FontFactory.getFontLarge());
        titleLabel.setFont(FontFactory.getFontLarge());

        searchField.setForeground(Color.BLACK);
        comboBox.setForeground(Color.BLACK);
        okButton.setForeground(Color.BLACK);
        cancelButton.setForeground(Color.BLACK);
        titleLabel.setForeground(Color.BLACK);
        frame.getContentPane().setBackground(Color.WHITE);

        frame.add(searchField);
        frame.add(comboBox);
        frame.add(okButton);
        frame.add(cancelButton);
        frame.add(titleLabel);



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
        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });



        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void setOKButtonActionListener(ActionListener actionListener) {
        okButton.addActionListener(actionListener);
    }

    public void setCancelButtonActionListener(ActionListener actionListener) {
        cancelButton.addActionListener(actionListener);
    }

    public void setOKButtonKeyListener(KeyListener keyListener) {
        okButton.addKeyListener(keyListener);
    }

    public String getSearch() {
        if (searchField.getText() == null) {
            return "";
        }
        return searchField.getText();
    }

    public int getSearchType() {
        return comboBox.getSelectedIndex();
    }

    public void close() {
        frame.dispose();
    }

    public static void main(String[] args) {
        SearchStudentFrame searchStudentFrame = new SearchStudentFrame();

    }
}//fim da classe SearchStudentFrame

