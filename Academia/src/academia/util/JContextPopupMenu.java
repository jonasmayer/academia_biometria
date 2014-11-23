
/*-
 * Classname:             JContextPopupMenu.java
 *
 * Version information:   0.5
 *
 * Date:                  05/04/2013 - 15:49:25
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.JTextComponent;

/**
 * Menu de contexto
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class JContextPopupMenu {

    //menu popup
    private final JPopupMenu popupMenu = new JPopupMenu();
    //itens do menu
    //itens do menu recortar
    private JMenuItem cutMenuItem;
    //itens do menu copiar
    private JMenuItem copyMenuItem;
    //itens do menu colar
    private JMenuItem pasteMenuItem;
    //itens do menu selecionar tudo
    private JMenuItem selectAllMenuItem;

    /**
     * Construtor sem parâmetros
     */
    public JContextPopupMenu() {
        //inicializa componentes
        cutMenuItem = new JMenuItem("Recortar");
        copyMenuItem = new JMenuItem("Copiar");
        pasteMenuItem = new JMenuItem("Colar");
        selectAllMenuItem = new JMenuItem("Selecionar tudo");

        //compoe menu popup
        popupMenu.add(cutMenuItem);
        popupMenu.add(copyMenuItem);
        popupMenu.add(pasteMenuItem);
        popupMenu.addSeparator();
        popupMenu.add(selectAllMenuItem);

        //define funções para o menu popup        
        ActionListener popupMenuListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == cutMenuItem) {
                    if (popupMenu.getInvoker() instanceof JTextComponent) {
                        JTextComponent textComponent = (JTextComponent) popupMenu.getInvoker();
                        textComponent.cut();
                    } else if (popupMenu.getInvoker() instanceof JAutoSuggestComboBox) {
                        JAutoSuggestComboBox autoSuggestComboBox = (JAutoSuggestComboBox) popupMenu.getInvoker();
                        autoSuggestComboBox.cut();
                    }

                } else if (source == copyMenuItem) {
                    if (popupMenu.getInvoker() instanceof JTextComponent) {
                        JTextComponent textComponent = (JTextComponent) popupMenu.getInvoker();
                        textComponent.copy();
                    } else if (popupMenu.getInvoker() instanceof JAutoSuggestComboBox) {
                        JAutoSuggestComboBox autoSuggestComboBox = (JAutoSuggestComboBox) popupMenu.getInvoker();
                        autoSuggestComboBox.copy();
                    }

                } else if (source == pasteMenuItem) {
                    if (popupMenu.getInvoker() instanceof JTextComponent) {
                        JTextComponent textComponent = (JTextComponent) popupMenu.getInvoker();
                        textComponent.paste();
                    } else if (popupMenu.getInvoker() instanceof JAutoSuggestComboBox) {
                        JAutoSuggestComboBox autoSuggestComboBox = (JAutoSuggestComboBox) popupMenu.getInvoker();
                        autoSuggestComboBox.paste();
                    }

                } else if (source == selectAllMenuItem) {
                    if (popupMenu.getInvoker() instanceof JTextComponent) {
                        JTextComponent textComponent = (JTextComponent) popupMenu.getInvoker();
                        textComponent.selectAll();
                    } else if (popupMenu.getInvoker() instanceof JAutoSuggestComboBox) {
                        JAutoSuggestComboBox autoSuggestComboBox = (JAutoSuggestComboBox) popupMenu.getInvoker();
                        autoSuggestComboBox.selectAll();
                    }
                }
            }
        };

        //adiciona eventos ao menu
        cutMenuItem.addActionListener(popupMenuListener);
        copyMenuItem.addActionListener(popupMenuListener);
        pasteMenuItem.addActionListener(popupMenuListener);
        selectAllMenuItem.addActionListener(popupMenuListener);
    }//fim do construtor

    /**
     * Adiciona o menu de contexto ao componente
     *
     * @param component componente
     */
    public void addInComponet(Component component) {

        //adiciona ouvinte ao component
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }//fim do método addInComponet
}//fim da classe JContextPopupMenu

