/*- 
 * Classname:             ThreadDeviceController.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  05/08/2013 - 16:10:04 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package academia.controller;

import academia.util.Observer;
import academia.view.ControlPainel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class ThreadDeviceController {

    private Thread thread = null;
    private volatile static ThreadDeviceController uniqueInstance;
    private ControlPainel view;
    private List<Observer> observers;
    private BiometricReaderController biometricReaderController;

    private ThreadDeviceController() {
        observers = new ArrayList();
        view = new ControlPainel();
        view.setActionListenerEnableNotificationButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (view.getNotificationWatcher()) {
                    button.setText("Ativar");
                    view.setNotificationWatcher(false);
                } else {
                    button.setText("Desativar");
                    view.setNotificationWatcher(true);
                }
            }
        });
    }

    public static ThreadDeviceController getInstance() {
        if (uniqueInstance == null) {
            synchronized (ThreadDeviceController.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ThreadDeviceController();
                }
            }
        }
        return uniqueInstance;
    }

    public synchronized void start() {
        try {
            if (thread == null) {

                biometricReaderController = new BiometricReaderController(view);
                /*   for (int i = 0; i < observers.size(); i++) {
                 biometricReaderController.registerObserver(observers.get(i));
                 }*/
                thread = new Thread(biometricReaderController);

                thread.start();
                view.addNote("Dispositivo iniciado!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public synchronized void stop() {
        try {
            if (thread != null) {
                thread.checkAccess();
                thread.interrupt();
                thread.join();
                thread = null;
                view.addNote("Dispositivo parado!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public   void refresh() {
        try {
            //   stop();            start();
             stop(); 
            biometricReaderController.refresh();
           start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public synchronized void pause() {
        try {
              System.out.println("PAUSE");
             
           thread.wait();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public synchronized void resume() {
        try {
              System.out.println("RESUME");
              
             thread.notify();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
        if (biometricReaderController != null) {
            biometricReaderController.registerObserver(observer);
        }
    }

    public void cleanerObservers() {

        if (biometricReaderController != null) {
            for (int i = 0; i < observers.size(); i++) {
                biometricReaderController.removeObserver(observers.get(i));
            }
            observers.clear();
        }
    }
}//fim da classe ThreadDeviceController  

