
/*-
 * Classname:             PreferencesManager.java
 *
 * Version information:   1.0
 *
 * Date:                  25/01/2013 - 14:25:20
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

import java.util.prefs.Preferences;

/**
 * Preferências do usuário
 *
 * @see java.util.prefs.Preferences
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class PreferencesManager {

    //preferências do sistema
    private static Preferences system = Preferences.systemRoot();
    //local do banco de dados
    public static final String KEY_LOCAL_DATABASE = "LocalDatabase";
    //usuário conexão com banco
    public static final String KEY_DATABASE_USER = "DatabaseUser";
    //senha do banco
    public static final String KEY_DATABASE_PASSWORD = "DatabasePassword";

    /**
     * Retorna as preferências
     *
     * @return <code>Preferences</code> preferências
     */
    public static Preferences getPreferences() {
        return system;
    }//fim do método getPreferences

    /**
     * Define preferência do sistema - local da base de dados
     *
     * @param localDatabase local da base de dados
     */
    public static void putLocalDatabase(String localDatabase) {
        system.put(KEY_LOCAL_DATABASE, localDatabase);
    }//fim do método putLocalDatabase

    /**
     * Define preferência do sistema - usuário da base de dados
     *
     * @param databaseUser usuário da base de dados
     */
    public static void putDatabaseUser(String databaseUser) {
        system.put(KEY_DATABASE_USER, databaseUser);
    }//fim do método putDatabaseUser

    /**
     * Define preferência do sistema - senha da base de dados
     *
     * @param databasePassword senha da base de dados
     */
    public static void putDatabasePassword(String databasePassword) {
        system.put(KEY_DATABASE_PASSWORD, databasePassword);
    }//fim do método putDatabasePassword
}//fim da classe PreferencesManager

