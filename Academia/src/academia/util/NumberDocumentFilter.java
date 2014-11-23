
/*-
* Classname:             NumberDocumentFilter.java
*
* Version information:   (versão)
*
* Date:                  15/02/2013 - 15:14:16
*
* author:                Jonas Mayer (jmayer13@hotmail.com)
* Copyright notice:      (informações do método, pra que serve, idéia principal)
*/

package academia.util;

import javax.swing.text.DocumentFilter;


/**
* Descrição
* @see
* @author Jonas Mayer (jmayer13@hotmail.com)
*/

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDocumentFilter extends DocumentFilter {

    public NumberDocumentFilter() {
    }
        @Override
    public void insertString(FilterBypass filterBypass, int offs, String str, AttributeSet a)
            throws BadLocationException {

        for (int i = 0; i < str.length(); i++) {
                     //verifica se é letra (se não continua)
            if (Character.isDigit(str.charAt(i)) == false) {
                return;
            }

        }
       filterBypass.insertString(offs, str, a);

    }

    @Override
    public void replace(FilterBypass fb, int offs,
            int length,
            String str, AttributeSet a)
            throws BadLocationException {

        for (int i = 0; i < str.length(); i++) {
                        if (Character.isDigit(str.charAt(i)) == false) {
                return;
            }
        }
        fb.replace(offs, length, str, a);
    }

}//fim da classe NumberDocumentFilter