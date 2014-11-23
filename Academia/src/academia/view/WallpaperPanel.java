
/*-
 * Classname:             WallpaperPanel.java
 *
 * Version information:   1.0
 *
 * Date:                  25/01/2013 - 14:23:56
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe responsável por gerar panel com plano de fundo
 *
 * @see javax.swing.JPanel
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class WallpaperPanel extends JPanel {

    //constantes representando as funções
    private final int BACKGROUND_DEFAULT = 1;
    private final int BACKGROUND_WHITE = 2;
    int option = 1;

    /**
     * Construtor sem parâmetro
     */
    public WallpaperPanel() {
    }//fim do construtor sem parâmetros

    /**
     * Construtor com opção de tela
     *
     * @param option opção de tela
     */
    public WallpaperPanel(int option) {
        this.option = option;
    }//fim do construtor

    /**
     * Método sobresctrito paintComponent para aplicar fundo
     *
     * @param g Graphics
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = null;

        //pega tamanho da tela
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        int width = screenResolution.getX();
        int height = screenResolution.getY();
        String separator = System.getProperty("file.separator");
        File testBackground = new File("image" + separator + "background.png");
        //carrega imagem padrão
        if (option == BACKGROUND_DEFAULT && testBackground.exists()) {
            imageIcon = new ImageIcon("image" + separator + "background.png");
            //tamanho da imagem
            int imageWidth = imageIcon.getIconWidth();
            int imageHeight = imageIcon.getIconHeight();

            int heightCompact = this.getWidth();
            int widthCompact = this.getHeight();
            if (imageWidth > this.getWidth() || imageHeight > this.getHeight()) {

                if ((imageWidth - this.getWidth()) / this.getWidth() >= (imageHeight - this.getHeight()) / this.getHeight()) {
                    int te = this.getWidth() * 100 / imageWidth;
                    heightCompact = imageHeight * te / 100;
                    assert (height > this.getHeight()) : "Falha no calculo de renderização de imagens";
                } else {
                    int te = this.getHeight() * 100 / imageHeight;
                    widthCompact = imageWidth * te / 100;
                    assert (width > this.getWidth()) : "Falha no calculo de renderização de imagens";
                }
                //redefine a imagem com o algoritmo eoo manaho especificados
                imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(widthCompact, heightCompact, Image.SCALE_AREA_AVERAGING));
                imageWidth = widthCompact;
                imageHeight = heightCompact;
            }
            //calcula coordenadas centralizadas
            int x = (this.getWidth() - imageWidth) / 2;
            int y = (this.getHeight() - imageHeight) / 2;
            //converte Imageicon para Image
            Image image = imageIcon.getImage();
            //define cor fundo
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            //adiciona imagem
            g.drawImage(image, x, y, imageWidth, imageHeight, Color.WHITE, this);
            }
        //configura cor padrão da uri
        if (option == BACKGROUND_WHITE) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
        }
    }//fim do método paintComponent

    //método para teste da interface
    public static void main(String[] args) {
        JPanel wallpaperPanel = new WallpaperPanel();
        wallpaperPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        JFrame frameTest = new JFrame();
        frameTest.setLayout(null);

        ScreenResolution screenResolution = ScreenResolution.getInstance();
        frameTest.setBounds(0, 0, 400, 400);
        wallpaperPanel.setBounds(0, 0, 400, 400);
        frameTest.add(wallpaperPanel);
        frameTest.setVisible(true);
        frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//fim do método main
}//fim da classe WallpaperPanel

