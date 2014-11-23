
/*-
 * Classname:             DateDocumentFilter.java
 *
 * Version information:   0.7
 *
 * Date:                  20/03/2013 - 15:32:56
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class DateDocumentFilter extends DocumentFilter {

    /**
     * Chamado antes de substituir uma região de texto no documento
     *
     * @param fb FilterBypass que podem ser usados para transformar o Document
     * @param offset Localização no Documento
     * @param length Comprimento de texto para excluir
     * @param string Texto para inserir, nulo indica nenhum texto para inserir
     * @param attrs indicando atributos do texto inserido,
     * @throws BadLocationException a posição de inserção dada não é uma posição
     * válida dentro do documento
     */
    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attrs)
            throws BadLocationException {
        Document document = fb.getDocument();
        if (offset < 10 && (document.getLength() + string.length()) - length <= 10) {
            fb.remove(offset, length);

            //se é um digito
            if (string.length() == 1) {

                //verificação de inserção antes de barra 
             /*   if (document.getLength() - offset > 0) {
                 System.err.println(string);
                 String content = null;
                 try {
                 content = document.getText(0, offset);
                 content = content + string;
                 System.out.println(offset + "   " + document.getLength() + "---" + document.getText(offset, document.getLength()));

                 content = content + document.getText(offset, document.getLength() - (offset + 1));
                 } catch (Exception ex) {
                 ex.printStackTrace();
                 System.exit(1);
                 }
                 if (content.charAt(1) == '/') {
                 content = "0" + content;
                 }
                 if (content.charAt(4) == '/') {
                 content = content.substring(0, 3) + "0" + content.substring(3);
                 System.err.println(content);
                 }
                 content = content.replaceAll("/", "");
                 System.out.println(content);
                 fb.remove(0, document.getLength());
                 for (int i = 0; i < content.length(); i++) {
                 this.replace(fb, document.getLength(), 0, "" + content.charAt(i), attrs);
                 }
                 this.replace(fb, 2, 0, "/", attrs);

                 return;
                 }*/


                //se é o digito é um número
                if (Character.isDigit(string.charAt(0))) {


                    //verificação de limites dia<31 mês<12
                    int value = Integer.valueOf(string);
                    if (offset == 0 && value > 3) {
                        string = 0 + string;
                    }
                    if (offset == 1 && value > 1) {
                        if (document.getText(0, 1).equals("3")) {
                            fb.remove(0, 1);
                            string = 31 + "";
                            offset = 0;
                        }
                    }
                    if (offset == 3 && value > 1) {
                        string = 0 + string;
                    }
                    if (offset == 4 && value > 2) {
                        if (document.getText(3, 1).equals("1")) {
                            fb.remove(3, 1);
                            string = 12 + "";
                            offset = 3;
                        }
                    }
                    //se estiver na posição 2 ou 5
                    if (offset == 2 || offset == 5) {
                        if (offset == 2 && value > 1) {
                            string = 0 + string;
                        }
                        //e não existir barra depois é inserida uma barra ao inicio da string 
                        if (document.getLength() >= offset + 1) {
                            if (!document.getText(offset + 1, 1).equals("/")) {
                                string = "/" + string;
                            }
                        } else {
                            string = "/" + string;
                        }

                    }


                    //insere a string
                    fb.replace(offset, 0, string, attrs);
                } else if (string.equals("/")) { //se é uma barra
                    //se a barra for digitada antes do 2 digito
                    if (offset == 1 || offset == 4) {
                        //adiciona-se um 0 a esquerda
                        fb.replace(offset - 1, length, "0", attrs);
                        //e uma barra
                        fb.replace(offset + 1, length, "/", attrs);
                    }
                    //se estiver na 2 ou 5 posição 
                    if (offset == 2 || offset == 5) {
                        fb.replace(offset, length, "/", attrs);
                    }
                }

            } else if (string.length() > 1) {
                if (offset == 0 && length == 0) {
                    if (document.getLength() > 0) {
                        String text = document.getText(0, document.getLength());
                        string = string.replaceAll("/", "") + text.replaceAll("/", "");

                        fb.remove(0, document.getLength());
                    } else {
                        // fb.remove(offset, length);
                    }

                    for (int i = 0; i < string.length(); i++) {
                        if (offset > 10 || document.getLength() >= 10) {
                            return;
                        }
                        char charNow = string.charAt(i);

                        if (Character.isDigit(charNow)) {
                            if (offset == 2 || offset == 5) {
                                fb.replace(offset, 0, "/", attrs);
                                offset++;
                            }
                            fb.replace(offset, 0, String.valueOf(charNow), attrs);
                        } else if (charNow == '/') {
                            if (offset == 1 || offset == 4) {
                                fb.replace(offset - 1, length, "0", attrs);
                            }
                            if (offset == 2 || offset == 5) {
                                fb.replace(offset, 0, "/", attrs);
                            }
                        }
                        offset++;

                    }
                } else {
                    if (document.getLength() > 0 && length == 0) {
                        String text = document.getText(0, document.getLength());
                        text = text.replaceAll("/", "");
                        string = string.replaceAll("/", "");
                        string = text.substring(0, offset - 1) + string + text.substring(offset - 1);
                        if (string.length() >= 10) {
                            string = string.substring(0, 9);
                        }
                        offset = 0;

                        fb.remove(0, document.getLength());
                    }

                    for (int i = 0; i < string.length(); i++) {
                        if (offset > 10 || fb.getDocument().getLength() >= 10) {
                            return;
                        }
                        char charNow = string.charAt(i);
                        if (Character.isDigit(charNow)) {
                            if (offset == 2 || offset == 5) {
                                fb.replace(offset, 0, "/", attrs);
                                offset++;
                            }
                            fb.replace(offset, 0, String.valueOf(charNow), attrs);
                        } else if (charNow == '/') {
                            if (offset == 2 || offset == 5) {
                                fb.replace(offset, 0, "/", attrs);
                            }
                        }
                        offset++;
                    }
                }
            }
        }
    }
}//fim da classe DateDocumentFilter

/*
 public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attrs)
 throws BadLocationException {
 Document document = fb.getDocument();
 if (offset < 10) {
 fb.remove(offset, length);
 //se é um digito
 if (string.length() == 1) {
 //se é o digito é um número
 if (Character.isDigit(string.charAt(0))) {
 //se estiver na posição 2 ou 5
 if (offset == 2 || offset == 5) {
 //e não existir barra depois é inserida uma barra ao inicio da string 
 if (document.getLength() >= offset + 1) {
 if (!document.getText(offset + 1, 1).equals("/")) {
 string = "/" + string;
 }
 } else {
 string = "/" + string;
 }

 }
 //insere a string
 fb.replace(offset, 0, string, attrs);
 } else if (string.equals("/")) { //se é uma barra
 //se a barra for digitada antes do 2 digito
 if (offset == 1 || offset == 4) {
 //adiciona-se um 0 a esquerda
 fb.replace(offset - 1, length, "0", attrs);
 //e uma barra
 fb.replace(offset + 1, length, "/", attrs);
 }
 //se estiver na 2 ou 5 posição 
 if (offset == 2 || offset == 5) {
 fb.replace(offset, length, "/", attrs);
 }
 }

 } else if (string.length() > 1) {
 if (offset == 0 && length == 0) {
 if (document.getLength() > 0) {
 String text = document.getText(0, document.getLength());
 string = string.replaceAll("/", "") + text.replaceAll("/", "");

 fb.remove(0, document.getLength());
 } else {
 // fb.remove(offset, length);
 }

 for (int i = 0; i < string.length(); i++) {
 if (offset > 10 || document.getLength() >= 10) {
 return;
 }
 char charNow = string.charAt(i);

 if (Character.isDigit(charNow)) {
 if (offset == 2 || offset == 5) {
 fb.replace(offset, 0, "/", attrs);
 offset++;
 }
 fb.replace(offset, 0, String.valueOf(charNow), attrs);
 } else if (charNow == '/') {
 if (offset == 1 || offset == 4) {
 fb.replace(offset - 1, length, "0", attrs);
 }
 if (offset == 2 || offset == 5) {
 fb.replace(offset, 0, "/", attrs);
 }
 }
 offset++;

 }
 } else {
 if (document.getLength() > 0 && length == 0) {
 String text = document.getText(0, document.getLength());
 text = text.replaceAll("/", "");
 string = string.replaceAll("/", "");
 string = text.substring(0, offset - 1) + string + text.substring(offset - 1);
 if (string.length() >= 10) {
 string = string.substring(0, 9);
 }
 offset = 0;

 fb.remove(0, document.getLength());
 }

 for (int i = 0; i < string.length(); i++) {
 if (offset > 10 || fb.getDocument().getLength() >= 10) {
 return;
 }
 char charNow = string.charAt(i);
 if (Character.isDigit(charNow)) {
 if (offset == 2 || offset == 5) {
 fb.replace(offset, 0, "/", attrs);
 offset++;
 }
 fb.replace(offset, 0, String.valueOf(charNow), attrs);
 } else if (charNow == '/') {
 if (offset == 2 || offset == 5) {
 fb.replace(offset, 0, "/", attrs);
 }
 }
 offset++;
 }
 }
 }
 }
 }
 */
