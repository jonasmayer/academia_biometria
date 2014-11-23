
/*-
 * Classname:             ScreenResolution.java
 *
 * Version information:   1.0
 *
 * Date:                  25/01/2013 - 14:08:44
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Pega tamanho da tela
 * @see java.awt.Dimension
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class ScreenResolution {

    //largura
    private int x = 0;
    //altura
    private int y = 0;
    //instancia única seguindo o padrão singleton
    private static ScreenResolution screenResolution;

    /**
     * Construtor de ScreenResolution responsável por pegar a resolução da tela
     */
    private ScreenResolution() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        x = (int) dim.getWidth();//largura
        y = (int) dim.getHeight();//altura
    }//fim do método construtor

    /**
     * Método para instância única
     * @return <code>ScreenResolution</code> com instância
     */
    public static ScreenResolution getInstance(){
        if(screenResolution==null){
            screenResolution = new ScreenResolution();
        }
        return screenResolution;
    }//fim do método getInstance

    /**
     * Obtêm a largura da tela
     * @return <code>Integer</code> com a largura
     */
    public int getX() {
        return x;
    }//fim do método getX

    /**
     * Obtêm a altura da tela
     * @return <code>Integer</code> com a altura
     */
    public int getY() {
        return y;
    }//fim do método getY
}//fim da classe ScreenResolution


