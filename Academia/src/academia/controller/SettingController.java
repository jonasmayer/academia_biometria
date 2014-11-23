
/*-
 * Classname:             SettingController.java
 *
 * Version information:   (versão)
 *
 * Date:                  04/03/2013 - 14:07:02
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.util.PreferencesManager;
import academia.view.SettingsFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class SettingController {

    private SettingsFrame settingsFrame;

    public SettingController() {
        Preferences preferences = PreferencesManager.getPreferences();
        String dataBaseLocal = preferences.get(PreferencesManager.KEY_LOCAL_DATABASE, null);
        String userName = preferences.get(PreferencesManager.KEY_DATABASE_USER, null);
        String password = preferences.get(PreferencesManager.KEY_DATABASE_PASSWORD, null);

        if (dataBaseLocal == null) {
            settingsFrame = new SettingsFrame();
        } else {
            settingsFrame = new SettingsFrame(dataBaseLocal, userName, password);
        }

        settingsFrame.setOkButtonactionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreferencesManager.putLocalDatabase(settingsFrame.getIP());
                PreferencesManager.putDatabaseUser(settingsFrame.getUserName());
                PreferencesManager.putDatabasePassword(settingsFrame.getPassword());
                settingsFrame.close();
            }
        });
        settingsFrame.setCancelButtonactionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingsFrame.close();
            }
        });
        settingsFrame.setCloseWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                settingsFrame.close();
            }
        });
    }
}//fim da classe SettingController

