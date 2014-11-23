
/*-
 * Classname:             Main.java
 *
 * Version information:   1.0
 *
 * Date:                  25/01/2013 - 17:20:28
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      COPYRIGHT 2013 Jonas Mayer
 */
package academia;

import academia.controller.MainController;
import academia.controller.SettingController;
import academia.controller.ThreadDeviceController;
import academia.util.LogMaker;
import academia.util.PreferencesManager;
import academia.util.SingleInstanceApplication;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 * Classe principal responsável por iniciar o programa
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class Main {

    public static void main(String[] args) {

        //checa instância unica
        if (SingleInstanceApplication.check()) {
            //bloqueia o arquivo
            SingleInstanceApplication.lock();

            Preferences preferences = PreferencesManager.getPreferences();
            if (preferences.get(PreferencesManager.KEY_LOCAL_DATABASE, "").isEmpty()) {
                SettingController settingController = new SettingController();

            } else {
                try {
                    ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
                    threadDeviceController.start();
                } catch (UnsatisfiedLinkError exception) {
                    JOptionPane.showMessageDialog(null, "O leitor não foi encontrado!" + exception.getMessage(), "Atenção!", JOptionPane.WARNING_MESSAGE);
                    exception.printStackTrace();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Erro:" + exception.getMessage(), "Atenção!", JOptionPane.WARNING_MESSAGE);
                    exception.printStackTrace();
                    LogMaker.create(exception);
                }
                MainController mainController = new MainController();
            }
        } else {
            JOptionPane.showMessageDialog(null, "O programa já está em execução!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//fim do método main
}//fim da classe Main

