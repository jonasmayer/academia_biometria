
/*-
 * Classname:             JAutoSuggestComboBox.java
 *
 * Version information:   0.5
 *
 * Date:                  05/04/2013 - 15:13:12
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

import java.awt.EventQueue;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Document;

/**
 * Componente para comboBox com sugestões
 *
 * @see javax.swing.JComboBox
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class JAutoSuggestComboBox extends JComboBox {

    //campo de texto interno
    private JTextField tf;
    //vetor com sugestões
    private Vector<String> vector = new Vector<String>();
    //frag de constrole de visualização
    private boolean hide_flag = false;

    /**
     * Construtor sem parâmetros
     */
    public JAutoSuggestComboBox() {
        //icicializa classe pai
        super();
        //pega textField interno
        tf = (JTextField) editor.getEditorComponent();
        //ativa edição
        setEditable(true);
        //adiciona eventos
        addEvent();
    }//fim do construtor sem parâmetros

    /**
     * Construtor com lista de sugestôes
     *
     * @param list lista de sugestões
     */
    public JAutoSuggestComboBox(List<String> list) {
        //chama construtor sem argumentos
        this();
        //define lista
        setSuggestList(list);
    }//fim do construtor com lista de sugestôes

    /**
     * Adiciona eventos
     */
    private void addEvent() {
        //define eventos para autocomplete
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                //cria uma thread para mostrar a combo
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        //pega texto atual do campo de texto interno
                        String text = tf.getText();
                        //se não há texto 
                        if (text.length() == 0) {
                            //esconde a popup
                            hidePopup();
                            //atualiza modelo
                            setModel(new DefaultComboBoxModel(vector), "");
                        } else {
                            //pega modelo
                            DefaultComboBoxModel model = getSuggestedModel(vector, text);
                            //se a modelo estiver vazio e a flag ativa
                            if (model.getSize() == 0 || hide_flag) {
                                //esconde a popup
                                hidePopup();
                                //desativa flag
                                hide_flag = false;
                            } else {//se a tag estiver ativa o campo de texto não estiver vazio e o modelo não estiver vazio
                                //atualiza modelo
                                setModel(model, text);
                                //mostra popup
                                showPopup();
                            }
                        }
                    }//fim do método run
                });
            }//fim do método keyTyped

            @Override
            public void keyPressed(KeyEvent e) {
                //pega texto atual do campo de texto interno
                String text = tf.getText();
                //pega código da tecla precionada
                int code = e.getKeyCode();
                //se a tecla precionada for enter
                if (code == KeyEvent.VK_ENTER) {
                    if (!vector.contains(text)) {
                        vector.addElement(text);
                        Collections.sort(vector);
                        setModel(getSuggestedModel(vector, text), text);
                    }
                    hide_flag = true;
                } else if (code == KeyEvent.VK_ESCAPE) {//se foe espaço
                    hide_flag = true;
                } else if (code == KeyEvent.VK_RIGHT) {//se for direcional direita
                    for (int i = 0; i < vector.size(); i++) {
                        String str = vector.elementAt(i);
                        if (str.startsWith(text)) {
                            setSelectedIndex(-1);
                            tf.setText(str);
                            return;
                        }
                    }
                }
            }
        });
        setModel(new DefaultComboBoxModel(vector), "");

    }//fim do método addEvent

    /**
     * Define a lista de sugestões
     *
     * @param list lista de sugestões
     */
    public void setSuggestList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            vector.add(list.get(i));
        }
        Collections.sort(vector);
    }//fim do método setSuggestList

    /**
     * Define/atualiza modelo da comboBox
     *
     * @param mdl modelo
     * @param str texto da combo
     */
    private void setModel(DefaultComboBoxModel mdl, String str) {
        setModel(mdl);
        setSelectedIndex(-1);
        JTextField tempField = (JTextField) getEditor().getEditorComponent();
        tempField.setText(str);
    }//fim do método setModel

    /**
     * Cria modelo a partir da lista de sugestões e do texto atual da comboBox
     *
     * @param list lista de sugestôes
     * @param text texto atual da comboBox
     * @return <code>DefaultComboBoxModel</code> modelo da comboBox
     */
    private static DefaultComboBoxModel getSuggestedModel(List<String> list, String text) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            //se item da lista inicia com string salva como possibilidade 
            if (s.toLowerCase().startsWith(text.toLowerCase())) {
                defaultComboBoxModel.addElement(s);
            }
        }
        return defaultComboBoxModel;
    }//fim do método getSuggestedModel

    /**
     * Define texto do textField interno
     *
     * @param text texto
     */
    public void setText(String text) {
        tf.setText(text);
    }//fim do método setText

    /**
     * Obtêm o texto do campo interno
     *
     * @return <code>String</code> texto
     */
    public String getText() {
        return tf.getText();
    }//fim do método getText

    /**
     * Define documento para o textField interno
     *
     * @param doc documento
     */
    public void setDocument(Document doc) {
        tf.setDocument(doc);
    }//fim do método setDocument

    /**
     * Obtêm documento do textField interno
     *
     * @return <code>Document</code> documento
     */
    public Document getDocument() {
        return tf.getDocument();
    }//fim do método getDocument

    /**
     * Adiciona evento a comboBox e ao textField interno
     *
     * @param mouseListener ouvinte
     */
    @Override
    public void addMouseListener(MouseListener mouseListener) {
        try {
            super.addMouseListener(mouseListener);
            if (tf != null) {
                tf.addMouseListener(mouseListener);
            }
        } catch (Exception exception) {
            System.err.println("Erro: não foi possível adicionar ouvinte de evento a JAutoSuggestComboBox!");
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível compor caixa de combinação com sugestão!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(exception);
        }
    }//fim do método addMouseListener

    /**
     * Adiciona evento a comboBox e ao textField interno
     *
     * @param focusListener ouvinte
     */
    @Override
    public void addFocusListener(FocusListener focusListener) {
        try {
            super.addFocusListener(focusListener);
            if (tf != null) {
                tf.addFocusListener(focusListener);
            }
        } catch (Exception exception) {
            System.err.println("Erro: não foi possível adicionar ouvinte de evento a JAutoSuggestComboBox!");
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível compor caixa de combinação com sugestão!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(exception);
        }
    }//fim do método addFocusListener

    /**
     * Recorta conteudo do campo de texto interno
     */
    public void cut() {
        tf.cut();
    }//fim do método 

    /**
     * Copia conteudo do campo de texto interno
     */
    public void copy() {
        tf.copy();
    }//fim do método copy

    /**
     * Cola conteudo do campo de texto interno
     */
    public void paste() {
        tf.paste();
    }//fim do método paste

    /**
     * Seleciona todo o conteudo do campo de texto interno
     */
    public void selectAll() {
        tf.selectAll();
    }//fim do método selectAll

    @Override
    public void setBorder(Border border) {
        if (tf != null) {
            tf.setBorder(border);
        }
    }
}//fim da classe JAutoSuggestComboBox

