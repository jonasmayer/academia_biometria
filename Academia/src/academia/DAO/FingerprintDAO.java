/*- 
 * Classname:             FingerprintDAO.java 
 * 
 * Version information:   (versão) 
 * 
 * Date:                  21/07/2013 - 16:13:09 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package academia.DAO;

import academia.model.Fingerprint;
import academia.util.LogMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class FingerprintDAO {

    //classe responsável pela conexão com o banco de dados
    private ConnectionDatabase connectionDatabase;
    //conexão com o banco
    private Connection connection;

    /**
     * Método construtor sem parâmetros
     */
    public FingerprintDAO() {
        takeConnection();
    }//fim do método construtor sem parãmetros

    /**
     * Pega conexão com o banco de dados
     */
    private void takeConnection() {
        connectionDatabase = ConnectionDatabase.getInstance();
        connection = connectionDatabase.getConnection();
    }//fim do método takeConnection

    public int addFingerprint(Fingerprint fingerprint) {
        int codePoint = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT cadastra_digital(?,?,?);");
            
            preparedStatement.setInt(1, fingerprint.getCodeStudent());
            preparedStatement.setString(2, fingerprint.getFinger());
            preparedStatement.setString(3, fingerprint.getFir());
            
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                System.out.println(result.getObject(1));
            }
            
            
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível adicionar impressão digital!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível adicionar impressão digital!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(sqlException);
        }
        return codePoint;
    }
    
    public List<Fingerprint> listFingerprint() {
        List<Fingerprint> fingerprints = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT codigo_digital, dedo, fir, codigo_aluno FROM lista_digitais();");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Fingerprint fingerprint = new Fingerprint();
                fingerprint.setCodeFingerPrint(resultSet.getInt(1));
                fingerprint.setFinger(resultSet.getString(2));
                fingerprint.setFir(resultSet.getString(3));
                fingerprint.setCodeStudent(resultSet.getInt(4));
                fingerprints.add(fingerprint);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível adicionar impressão digital!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível adicionar impressão digital!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(sqlException);
        }
        return fingerprints;
    }
    
    public List<Fingerprint> listFingerprintStudent(int codeStudent) {
        List<Fingerprint> fingerprints = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT codigo_digital, dedo, fir, codigo_aluno FROM lista_digitais_aluno(?);");
            preparedStatement.setInt(1, codeStudent);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Fingerprint fingerprint = new Fingerprint();
                fingerprint.setCodeFingerPrint(resultSet.getInt(1));
                fingerprint.setFinger(resultSet.getString(2));
                fingerprint.setFir(resultSet.getString(3));
                fingerprint.setCodeStudent(resultSet.getInt(4));
                fingerprints.add(fingerprint);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível adicionar impressão digital!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível adicionar impressão digital!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(sqlException);
        }
        return fingerprints;
    }
}//fim da classe FingerprintDAO  

