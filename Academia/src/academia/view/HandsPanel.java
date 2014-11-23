/*- 
 * Classname:             HandsPanel.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  13/08/2013 - 16:21:29 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package academia.view;

import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class HandsPanel extends JPanel {

    private RoundButton rThumbButton;
    private RoundButton rIndexButton;
    private RoundButton rMiddleButton;
    private RoundButton rRingButton;
    private RoundButton rLittleButton;
    private RoundButton lThumbButton;
    private RoundButton lIndexButon;
    private RoundButton lMiddleButton;
    private RoundButton lRingButton;
    private RoundButton lLittleButton;
    private List<JButton> scannedList;
    private JButton t;
    public static final byte UNKNOWN = 0;
    public static final byte RIGHT_THUMB = 1;
    public static final byte RIGHT_INDEX = 2;
    public static final byte RIGHT_MIDDLE = 3;
    public static final byte RIGHT_RING = 4;
    public static final byte RIGHT_LITTLE = 5;
    public static final byte LEFT_THUMB = 6;
    public static final byte LEFT_INDEX = 7;
    public static final byte LEFT_MIDDLE = 8;
    public static final byte LEFT_RING = 9;
    public static final byte LEFT_LITTLE = 10;

    public HandsPanel() {
        scannedList = new ArrayList();
        createView();


    }

    private void createView() {
        rThumbButton = new RoundButton();
        rIndexButton = new RoundButton();
        rMiddleButton = new RoundButton();
        rRingButton = new RoundButton();
        rLittleButton = new RoundButton();
        lThumbButton = new RoundButton();
        lIndexButon = new RoundButton();
        lMiddleButton = new RoundButton();
        lRingButton = new RoundButton();
        lLittleButton = new RoundButton();
        setLayout(null);

        lThumbButton.setBounds(7, 151, 20, 20);
        lIndexButon.setBounds(61, 54, 20, 20);
        lMiddleButton.setBounds(100, 30, 20, 20);
        lRingButton.setBounds(150, 46, 20, 20);
        lLittleButton.setBounds(187, 77, 20, 20);
        rThumbButton.setBounds(420, 151, 20, 20);
        rIndexButton.setBounds(365, 54, 20, 20);
        rMiddleButton.setBounds(325, 30, 20, 20);
        rRingButton.setBounds(277, 46, 20, 20);
        rLittleButton.setBounds(240, 77, 20, 20);


        lThumbButton.setBackground(Color.GREEN);
        lIndexButon.setBackground(Color.GREEN);
        lMiddleButton.setBackground(Color.GREEN);
        lRingButton.setBackground(Color.GREEN);
        lLittleButton.setBackground(Color.GREEN);
        rThumbButton.setBackground(Color.GREEN);
        rIndexButton.setBackground(Color.GREEN);
        rMiddleButton.setBackground(Color.GREEN);
        rRingButton.setBackground(Color.GREEN);
        rLittleButton.setBackground(Color.GREEN);

        //default
        t = rThumbButton;
        rThumbButton.setBackground(Color.YELLOW);

        add(rThumbButton);
        add(rIndexButton);
        add(rMiddleButton);
        add(rRingButton);
        add(rLittleButton);
        add(lThumbButton);
        add(lIndexButon);
        add(lMiddleButton);
        add(lRingButton);
        add(lLittleButton);


        ActionListener ac = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                t = button;
                repaintFingers();

            }
        };

        lThumbButton.addActionListener(ac);
        lIndexButon.addActionListener(ac);
        lMiddleButton.addActionListener(ac);
        lRingButton.addActionListener(ac);
        lLittleButton.addActionListener(ac);
        rThumbButton.addActionListener(ac);
        rIndexButton.addActionListener(ac);
        rMiddleButton.addActionListener(ac);
        rRingButton.addActionListener(ac);
        rLittleButton.addActionListener(ac);
    }

    private void repaintFingers() {
        lThumbButton.setBackground(Color.GREEN);
        lIndexButon.setBackground(Color.GREEN);
        lMiddleButton.setBackground(Color.GREEN);
        lRingButton.setBackground(Color.GREEN);
        lLittleButton.setBackground(Color.GREEN);
        rThumbButton.setBackground(Color.GREEN);
        rIndexButton.setBackground(Color.GREEN);
        rMiddleButton.setBackground(Color.GREEN);
        rRingButton.setBackground(Color.GREEN);
        rLittleButton.setBackground(Color.GREEN);

        for (int i = 0; i < scannedList.size(); i++) {
            scannedList.get(i).setBackground(Color.red);
        }
        if (t != null) {
            t.setBackground(Color.YELLOW);
        }
    }

    public void setScannedFinger(byte finger) {

        if (finger == HandsPanel.LEFT_THUMB) {
            scannedList.add(lThumbButton);
        } else if (finger == HandsPanel.LEFT_INDEX) {
            scannedList.add(lIndexButon);
        } else if (finger == HandsPanel.LEFT_MIDDLE) {
            scannedList.add(lMiddleButton);
        } else if (finger == HandsPanel.LEFT_RING) {
            scannedList.add(lRingButton);
        } else if (finger == HandsPanel.LEFT_LITTLE) {
            scannedList.add(lLittleButton);
        } else if (finger == HandsPanel.RIGHT_THUMB) {
            scannedList.add(rThumbButton);
        } else if (finger == HandsPanel.RIGHT_INDEX) {
            scannedList.add(rIndexButton);
        } else if (finger == HandsPanel.RIGHT_MIDDLE) {
            scannedList.add(rMiddleButton);
        } else if (finger == HandsPanel.RIGHT_RING) {
            scannedList.add(rRingButton);
        } else if (finger == HandsPanel.RIGHT_LITTLE) {
            scannedList.add(rLittleButton);
        }
        repaintFingers();
    }

    public byte getFingerSelected() {
        if (t == lThumbButton) {
            return HandsPanel.LEFT_THUMB;

        } else if (t == lIndexButon) {
            return HandsPanel.LEFT_INDEX;

        } else if (t == lMiddleButton) {
            return HandsPanel.LEFT_MIDDLE;

        } else if (t == lRingButton) {
            return HandsPanel.LEFT_RING;

        } else if (t == lLittleButton) {
            return HandsPanel.LEFT_LITTLE;

        } else if (t == rThumbButton) {
            return HandsPanel.RIGHT_THUMB;

        } else if (t == rIndexButton) {
            return HandsPanel.RIGHT_INDEX;

        } else if (t == rMiddleButton) {
            return HandsPanel.RIGHT_MIDDLE;

        } else if (t == rRingButton) {
            return HandsPanel.RIGHT_RING;

        } else if (t == rLittleButton) {
            return HandsPanel.RIGHT_LITTLE;

        }
        return 0;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = null;
        String separator = System.getProperty("file.separator");
        File testBackground = new File("image" + separator + "hands.png");
        if (testBackground.exists()) {
            imageIcon = new ImageIcon("image" + separator + "hands.png");
            //tamanho da imagem
            int imageWidth = imageIcon.getIconWidth();
            int imageHeight = imageIcon.getIconHeight();

            //converte Imageicon para Image
            Image image = imageIcon.getImage();
            //define cor fundo
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, imageWidth, imageHeight);
            //adiciona imagem
            g.drawImage(image, 0, 0, imageWidth, imageHeight, Color.WHITE, this);
        } else {
            System.out.println("Imagem não encontrada");
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

    }//fim do método paintComponent

    private class RoundButton extends JButton {

        Shape shape;

        public RoundButton() {
            super();
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(getBackground());
            }
            g.fillOval(0, 0, getSize().width - 1,
                    getSize().height - 1);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(new Color(0, 0, 0, 50));
            g.drawOval(0, 0, getSize().width - 1,
                    getSize().height - 1);
        }

        @Override
        public boolean contains(int x, int y) {
            if (shape == null
                    || !shape.getBounds().equals(getBounds())) {
                shape = new Ellipse2D.Float(0, 0,
                        getWidth(), getHeight());
            }
            return shape.contains(x, y);
        }
    }

    public static void main(String args[]) {
        JPanel wallpaperPanel = new HandsPanel();
        wallpaperPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        JFrame frameTest = new JFrame();
        frameTest.setLayout(null);

        ScreenResolution screenResolution = ScreenResolution.getInstance();
        frameTest.setBounds(0, 0, 500, 500);
        wallpaperPanel.setBounds(0, 0, 450, 320);
        frameTest.add(wallpaperPanel);
        frameTest.setVisible(true);
        frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}//fim da classe HandsPanel  

