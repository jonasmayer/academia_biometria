
/*-
 * Classname:             MainController.java
 *
 * Version information:   (versão)
 *
 * Date:                  22/02/2013 - 02:20:02
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.ConnectionDatabase;
import academia.util.LogMaker;
import academia.util.ObserverInterface;
import academia.util.SubjectInterface;
import academia.view.MainFrame;
import academia.view.WallpaperPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class MainController implements ObserverInterface {

    private MainFrame mainFrame;
    private SubjectInterface addStudentController;

    public MainController() {
        mainFrame = new MainFrame();
        mainFrame.changePanel(new WallpaperPanel(1));

        mainFrame.setActionListenerAddStudentMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudentEvent();
            }
        });

        mainFrame.setActionListenerSeeStudentMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seeStudents();
            }
        });

        mainFrame.setActionListenerSearchStudentMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();

            }
        });

        mainFrame.setActionListenerPresentStudentMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seePresentStudent();
            }
        });
        mainFrame.setActionListenerSettingsSystemMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SettingController settingController = new SettingController();
            }
        });

        mainFrame.setActionListenerSeePointMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seePointMenu();
            }
        });
        mainFrame.setActionListenerAcessNotesMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notificationSystem();
            }
        });
        mainFrame.setActionListenerManagerTrainingMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerEquipmentMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerRegisterPayMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerSeeEmployeeMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerAddEmployeeMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerGeneralStatisticsMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerPayStatisticsMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerFrequencyStatisticMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Not supported yet.");
            }
        });
        mainFrame.setActionListenerAboutSystemMenuItem(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });


        mainFrame.setCloseWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
            }
        });

    }

    private void addStudentEvent() {
        addStudentController = new AddStudentController();
        addStudentController.registerObserver(this);
        addStudentController.notifyObservers();
    }

    private void seeStudents() {
        SeeStudentsController seeStudentsController = new SeeStudentsController();
        mainFrame.changePanel(seeStudentsController.getPanel());
        seeStudentsController.registerInAllObservers(this);
    }

    private void searchStudent() {
        SearchStudentController searchStudentController = new SearchStudentController();
        searchStudentController.registerObserver(this);
        searchStudentController.notifyObservers();
    }

    private void seePresentStudent() {
        SeePresentStudentController seePresentstudentController = new SeePresentStudentController();
        seePresentstudentController.registerInAllObserbers(this);
        mainFrame.changePanel(seePresentstudentController.getPanel());
    }

    private void seePointMenu() {
        PointController pointController = new PointController();
        pointController.registerObserver(this);
        pointController.notifyObservers();
    }

    private void notificationSystem() {
        NotificationController notificationController = new NotificationController();
        notificationController.registerAllObservers(this);
        mainFrame.changePanel(notificationController.getPanel());
    }

    private void disconnect() {
        try {
            ConnectionDatabase connectionDatabase = ConnectionDatabase.getInstance();
            connectionDatabase.close();
            mainFrame.exit();
            ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
            threadDeviceController.stop();
            System.exit(0);
        } catch (Exception ex) {
            LogMaker.create(ex);
            System.err.println("Falhaao fechar programa");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void update(boolean active, Object object) {
        mainFrame.setEnable(active);
        if (active == true) {
            mainFrame.requestFrameFocus();
        }
        if (object instanceof SeeStudentsController) {
            SeeStudentsController seeStudentsController = (SeeStudentsController) object;
            mainFrame.changePanel(seeStudentsController.getPanel());
            seeStudentsController.registerInAllObservers(this);
        }
    }
}//fim da classe MainController

