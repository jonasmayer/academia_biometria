
/*-
 * Classname:             ConnectionDatabase.java
 *
 * Version information:   1.0
 *
 * Date:                  25/01/2013 - 17:18:07
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      COPYRIGHT 2013 Jonas Mayer
 */
package academia.DAO;

import academia.util.LogMaker;
import academia.util.PreferencesManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;


/*
 * PP: Está classe ultiliza o padrão Singleton
 */
/**
 * Classe responsável pela conexão com o banco de dados
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class ConnectionDatabase {

    //instância da classe
    private static ConnectionDatabase connectionDatabase;
    //url completa para o banco
    private String databaseUrl;
    //local do banco (ip)
    private String localDatabase;
    //nome da base de dados
    private final String DATABASE_NAME = "Academia";
    //nome de usuário
    private String username;
    //senha do usuário
    private String password;
    //classe Preferences que pega dados
    private Preferences preferences;
    //conexão com o banco
    private Connection connection;

    /**
     * Construtor privado de ConnectionDatabase
     */
    private ConnectionDatabase() {
        //obtêm os dados de preferences
        preferences = PreferencesManager.getPreferences();
        localDatabase = preferences.get(PreferencesManager.KEY_LOCAL_DATABASE, null);
        username = preferences.get(PreferencesManager.KEY_DATABASE_USER, null);
        password = preferences.get(PreferencesManager.KEY_DATABASE_PASSWORD, null);
        databaseUrl = "jdbc:postgresql://" + localDatabase + "/" + DATABASE_NAME;

        //carrega
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Erro: classe do Driver do banco não encontrada!");
            classNotFoundException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: Driver do banco não encontrado!",
                    "Erro!", JOptionPane.WARNING_MESSAGE);
            classNotFoundException.printStackTrace();
            LogMaker.create(classNotFoundException);
        }
        //estabelece uma conexão com o banco
        try {
            connection = DriverManager.getConnection(databaseUrl, username, password);
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possivel realizar uma conexão com o banco de dados!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possivel conectar-se ao banco de dados!",
                    "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }//fim do construtor de ConnectionDatabase

    /**
     * Método que retorna instancia da classe (Singleton)
     *
     * @return <code>ConnectionDatabase</code> instância da classe
     */
    public static ConnectionDatabase getInstance() {
        if (connectionDatabase == null) {
            synchronized (ConnectionDatabase.class) {
                if (connectionDatabase == null) {

                    connectionDatabase = new ConnectionDatabase();
                }
            }
        }
        return connectionDatabase;
    }//fim do método getInstance

    /**
     * Obtêm a conexão com o banco
     *
     * @return <code>Connection</code> conexão com o banco
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Fecha a conexão com o banco de dados
     */
    public void close() {
        try {
            //fecha conexão
            connection.close();
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possivel fechar a conexão com o banco de dados!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possivel fechar a conexão ao banco de dados!",
                    "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        //zera instância da classe
        connectionDatabase = null;
    }//fim do método close
}//fim da classe ConnectionDatabase

