
/*-
 * Classname:             DecimalNumberDocumentFilter.java
 *
 * Version information:   1.0
 *
 * Date:                  12/04/2013 - 16:01:42
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
public class DecimalNumberDocumentFilter extends DocumentFilter {

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
        Document document = fb.getDocument();
        if (length > 0) {
            fb.remove(offset, length);
        }
        for (int i = 0; i < string.length(); i++) {
            char charNow = string.charAt(i);
            if (Character.isDigit(charNow)) {
                fb.replace(offset, 0, String.valueOf(charNow), attrs);
            } else if (charNow == '.' || charNow == ',') {
                if (!document.getText(0, document.getLength()).contains(".")) {
                    fb.replace(offset, 0, ".", attrs);

                }
            }
            offset++;
        }
    }
}//fim da classe DecimalNumberDocumentFilter

