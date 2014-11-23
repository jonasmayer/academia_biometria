
/*-
 * Classname:             MainFrame.java
 *
 * Version information:   (versão)
 *
 * Date:                  13/02/2013 - 17:16:48
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.controller.ThreadDeviceController;
import academia.util.FontFactory;
import academia.util.LogMaker;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class MainFrame {

    private JFrame frame;
    private JPanel insidePanel;
    private JPanel backgroundPanel;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu studentsMenu;
    private JMenu pointMenu;
    private JMenu trainingMenu;
    private JMenu payMenu;
    private JMenu employeeMenu;
    private JMenu statisticsMenu;
    private JMenu systemMenu;
    private JMenuItem seeStudentMenuItem;
    private JMenuItem addStudentMenuItem;
    private JMenuItem searchStudentMenuItem;
    private JMenuItem seePointMenuItem;
    private JMenuItem presentStudentMenuItem;
    private JMenuItem acessNotesMenuItem;
    private JMenuItem managerTrainingMenuItem;
    private JMenuItem equipmentMenuItem;
    private JMenuItem registerPayMenuItem;
    private JMenuItem seeEmployeeMenuItem;
    private JMenuItem addEmployeeMenuItem;
    private JMenuItem generalStatisticsMenuItem;
    private JMenuItem payStatisticsMenuItem;
    private JMenuItem frequencyStatisticMenuItem;
    private JMenuItem settingsSystemMenuItem;
    private JMenuItem aboutSystemMenuItem;
    private int x;
    private int y;

    public MainFrame() {
        createView();
    }

    private void createView() {
        //inicializa componentes
        frame = new JFrame("Acadêmia Atmosfera");
        backgroundPanel = new WallpaperPanel();
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menuBar = new JMenuBar();
        studentsMenu = new JMenu("Alunos");
        pointMenu = new JMenu("Pontos");
        trainingMenu = new JMenu("Treinamento");
        payMenu = new JMenu("Pagamentos");
        employeeMenu = new JMenu("Empregados");
        statisticsMenu = new JMenu("Estatísticas");
        systemMenu = new JMenu("Sistema");


        addStudentMenuItem = new JMenuItem("Adicionar");
        seeStudentMenuItem = new JMenuItem("Ver");
        searchStudentMenuItem = new JMenuItem("Buscar");

        seePointMenuItem = new JMenuItem("Visualizar");
        presentStudentMenuItem = new JMenuItem("Alunos presentes");
        acessNotesMenuItem = new JMenuItem("Notas de Acesso");

        managerTrainingMenuItem = new JMenuItem("Gerênciar");
        equipmentMenuItem = new JMenuItem("Aparelhos");
        registerPayMenuItem = new JMenuItem("Registrar");
        seeEmployeeMenuItem = new JMenuItem("Visualizar");
        addEmployeeMenuItem = new JMenuItem("Cadastrar");
        generalStatisticsMenuItem = new JMenuItem("Gerais");
        payStatisticsMenuItem = new JMenuItem("Pagamento");
        frequencyStatisticMenuItem = new JMenuItem("Frêquencia");
        settingsSystemMenuItem = new JMenuItem("Configurações");
        aboutSystemMenuItem = new JMenuItem("Sobre");

        //define layout como nulo
        frame.setLayout(null);
        backgroundPanel.setLayout(null);

        //busca tamanho da tela
        takeScreenSize();

        //define localização e tamanho dos componentes
        frame.setBounds(0, 0, x, y);
        scrollPane.setBounds(0, 35, x, y - 100);
        //barra de menus
        menuBar.setBounds(0, 0, x, 35);

        studentsMenu.setFont(FontFactory.getFontDefault());
        studentsMenu.setFont(FontFactory.getFontDefault());
        pointMenu.setFont(FontFactory.getFontDefault());
        trainingMenu.setFont(FontFactory.getFontDefault());
        payMenu.setFont(FontFactory.getFontDefault());
        employeeMenu.setFont(FontFactory.getFontDefault());
        statisticsMenu.setFont(FontFactory.getFontDefault());
        systemMenu.setFont(FontFactory.getFontDefault());
        seeStudentMenuItem.setFont(FontFactory.getFontDefault());
        addStudentMenuItem.setFont(FontFactory.getFontDefault());
        searchStudentMenuItem.setFont(FontFactory.getFontDefault());
        seePointMenuItem.setFont(FontFactory.getFontDefault());
        presentStudentMenuItem.setFont(FontFactory.getFontDefault());
        acessNotesMenuItem.setFont(FontFactory.getFontDefault());
        managerTrainingMenuItem.setFont(FontFactory.getFontDefault());
        equipmentMenuItem.setFont(FontFactory.getFontDefault());
        registerPayMenuItem.setFont(FontFactory.getFontDefault());
        seeEmployeeMenuItem.setFont(FontFactory.getFontDefault());
        addEmployeeMenuItem.setFont(FontFactory.getFontDefault());
        generalStatisticsMenuItem.setFont(FontFactory.getFontDefault());
        payStatisticsMenuItem.setFont(FontFactory.getFontDefault());
        frequencyStatisticMenuItem.setFont(FontFactory.getFontDefault());
        settingsSystemMenuItem.setFont(FontFactory.getFontDefault());
        aboutSystemMenuItem.setFont(FontFactory.getFontDefault());

        //junção de componentes
        frame.setContentPane(backgroundPanel);
        scrollPane.setBackground(Color.WHITE);
        frame.add(scrollPane);
        //adiciona itens de menu ao menu

        studentsMenu.add(addStudentMenuItem);
        studentsMenu.add(seeStudentMenuItem);
        studentsMenu.add(searchStudentMenuItem);
        pointMenu.add(seePointMenuItem);
        pointMenu.add(presentStudentMenuItem);
        pointMenu.add(acessNotesMenuItem);
        trainingMenu.add(managerTrainingMenuItem);
        trainingMenu.add(equipmentMenuItem);
        payMenu.add(registerPayMenuItem);
        employeeMenu.add(seeEmployeeMenuItem);
        employeeMenu.add(addEmployeeMenuItem);
        statisticsMenu.add(generalStatisticsMenuItem);
        statisticsMenu.add(payStatisticsMenuItem);
        statisticsMenu.add(frequencyStatisticMenuItem);
        systemMenu.add(settingsSystemMenuItem);
        systemMenu.add(aboutSystemMenuItem);
        menuBar.add(studentsMenu);
        menuBar.add(pointMenu);
        //menuBar.add(trainingMenu);
        //menuBar.add(payMenu);
        //menuBar.add(employeeMenu);
        //menuBar.add(statisticsMenu);
        menuBar.add(systemMenu);
        frame.add(menuBar);
        //define visibilidade
        frame.setVisible(true);
        scrollPane.setVisible(true);

        //configurações finais do frame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }//fim do método createView

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void changePanel(JPanel panel) {
         if (panel != null) {
            if (insidePanel != null) {
                //remove panel antigo
                //   scrollPane
            }
            //atualiza panel
            insidePanel = panel;
            //configurações
            //insidePanel.setLocation(0, 0);
            //    insidePanel.setSize(x, y);

            //adiciona panel
            scrollPane.setViewportView(insidePanel);
            scrollPane.setVisible(true);
        } else {
            try {
                if (insidePanel != null) {
                    frame.remove(insidePanel);
                }
                scrollPane.setVisible(false);
            } catch (NullPointerException nullPointerException) {
                System.err.println("Erro: não foi possível remover componente!");
                nullPointerException.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro: ao trocar telas!", "Erro!", JOptionPane.WARNING_MESSAGE);
                LogMaker.create(nullPointerException);
            }
        }
    }//fim do método changePanel

    public void setActionListenerAddStudentMenuItem(ActionListener actionListener) {
        addStudentMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerSeeStudentMenuItem(ActionListener actionListener) {
        seeStudentMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerSearchStudentMenuItem(ActionListener actionListener) {
        searchStudentMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerPresentStudentMenuItem(ActionListener actionListener) {
        presentStudentMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerSettingsSystemMenuItem(ActionListener actionListener) {
        settingsSystemMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerSeePointMenuItem(ActionListener actionListener) {
        seePointMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerAcessNotesMenuItem(ActionListener actionListener) {
        acessNotesMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerManagerTrainingMenuItem(ActionListener actionListener) {
        managerTrainingMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerEquipmentMenuItem(ActionListener actionListener) {
        equipmentMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerRegisterPayMenuItem(ActionListener actionListener) {
        registerPayMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerSeeEmployeeMenuItem(ActionListener actionListener) {
        seeEmployeeMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerAddEmployeeMenuItem(ActionListener actionListener) {
        addEmployeeMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerGeneralStatisticsMenuItem(ActionListener actionListener) {
        generalStatisticsMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerPayStatisticsMenuItem(ActionListener actionListener) {
        payStatisticsMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerFrequencyStatisticMenuItem(ActionListener actionListener) {
        frequencyStatisticMenuItem.addActionListener(actionListener);
    }

    public void setActionListenerAboutSystemMenuItem(ActionListener actionListener) {
        aboutSystemMenuItem.addActionListener(actionListener);
    }

    /**
     * Define visibilidade do frame principal
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }//fim do métodosetVisible

    /**
     * Fecha o frame principal
     */
    public void exit() {
        frame.dispose();
    }//fim do método exit

    /**
     * Define se frame está ativo
     *
     * @param enable verdadeiro-ativar falso-desativar
     */
    public void setEnable(boolean enable) {
        frame.setEnabled(enable);
    }//fim do método enableFrame

    public void requestFrameFocus() {
        frame.requestFocus();
    }
    // Método para teste da interface

    public static void main(String args[]) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.changePanel(new WallpaperPanel(2));

    }//fim do método main

    public void setCloseWindowListener(WindowListener windowListener) {
        frame.addWindowListener(windowListener);

    }
}//fim da classe MainFrame

