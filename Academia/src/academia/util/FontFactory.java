
/*-
* Classname:             FontFactory.java
*
* Version information:   (versão)
*
* Date:                  23/01/2013 - 14:02:34
*
* author:                Jonas Mayer (jmayer13@hotmail.com)
* Copyright notice:      (informações do método, pra que serve, idéia principal)
*/

package academia.util;

import java.awt.Font;


/**
* Descrição
* @see
* @author Jonas Mayer (jmayer13@hotmail.com)
*/
public class FontFactory {
    //fonte padrão

    private static Font fontDefault;
    //fonte grande
    private static Font fontLarge;

    /**
     * Construtor sem parâmetros
     */
    public FontFactory() {
    }//fim do construtor

    /**
     * Obtêm a fonte padrão
     * @return <code>Font</code> fonte padrão
     */
    public static Font getFontDefault() {
        fontDefault = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        return fontDefault;
    }//fim do método getFontDefault


    /**
     * Obtêm a fonte grande
     * @return <code>Font</code> fonte grande
     */
    public static Font getFontLarge() {
        fontLarge = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        return fontLarge;
    }//fim do método getFontLarge
}//fim da classe FontFactory