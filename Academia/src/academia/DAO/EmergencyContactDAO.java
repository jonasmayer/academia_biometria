
/*-
 * Classname:             EmergencyContactDAO.java
 *
 * Version information:   (versão)
 *
 * Date:                  07/02/2013 - 17:20:39
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.DAO;

import academia.model.EmergencyContact;
import academia.model.Student;
import academia.util.LogMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class EmergencyContactDAO {
    //classe responsável pela conexão com o banco de dados

    private ConnectionDatabase connectionDatabase;
    //conexão com o banco
    private Connection connection;

    /**
     * Método construtor sem parâmetros
     */
    public EmergencyContactDAO() {
        takeConnection();
    }//fim do método construtor sem parãmetros

    /**
     * Pega conexão com o banco de dados
     */
    private void takeConnection() {
        connectionDatabase = ConnectionDatabase.getInstance();
        connection = connectionDatabase.getConnection();
    }//fim do método takeConnection

    public void registerEmergencyContact(EmergencyContact emergencyContact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT cadastra_contato(?,?,?,?,?,?);");
            preparedStatement.setInt(1, emergencyContact.getCodeStudent());
            preparedStatement.setString(2, emergencyContact.getName());
            preparedStatement.setString(3, emergencyContact.getPhone());
            preparedStatement.setString(4, emergencyContact.getCity());
            preparedStatement.setString(5, emergencyContact.getUF());
            preparedStatement.setString(6, emergencyContact.getRelationship());

            preparedStatement.execute();

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível adicionar contato de emergencia!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível adicionar contato de emergencia!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }

    public void editEmergencyContact(EmergencyContact emergencyContact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT edita_contato(?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, emergencyContact.getCodeContact());
            preparedStatement.setInt(2, emergencyContact.getCodeStudent());
            preparedStatement.setString(3, emergencyContact.getName());
            preparedStatement.setString(4, emergencyContact.getPhone());
            preparedStatement.setString(5, emergencyContact.getCity());
            preparedStatement.setString(6, emergencyContact.getUF());
            preparedStatement.setString(7, emergencyContact.getRelationship());

            preparedStatement.execute();

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível editar contato de emergencia!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível editar contato de emergencia!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }

    public EmergencyContact searchEmergencyContact(Student student) {
        EmergencyContact emergencyContact = new EmergencyContact();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT busca_contato.codigo_contato ,busca_contato.codigo_aluno ,busca_contato.nome ,busca_contato.telefone ,busca_contato.cidade ,busca_contato.uf ,busca_contato.parentesco FROM busca_contato(?);");
            preparedStatement.setInt(1, student.getCodeStudent());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                emergencyContact.setCodeContact(resultSet.getInt(1));
                emergencyContact.setCodeStudent(resultSet.getInt(2));
                emergencyContact.setName(resultSet.getString(3));
                emergencyContact.setPhone(resultSet.getString(4));
                emergencyContact.setCity(resultSet.getString(5));
                emergencyContact.setUF(resultSet.getString(6));
                emergencyContact.setRelationship(resultSet.getString(7));
            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar contato de emergencia pelo código do aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar contato de emergencia!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return emergencyContact;
    }
}//fim da classe EmergencyContactDAO

