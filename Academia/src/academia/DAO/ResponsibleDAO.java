
/*-
 * Classname:             ResponsibleDAO.java
 *
 * Version information:   (versão)
 *
 * Date:                  07/02/2013 - 22:19:38
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.DAO;

import academia.model.Responsible;
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
public class ResponsibleDAO {
    //classe responsável pela conexão com o banco de dados

    private ConnectionDatabase connectionDatabase;
    //conexão com o banco
    private Connection connection;

    /**
     * Método construtor sem parâmetros
     */
    public ResponsibleDAO() {
        takeConnection();
    }//fim do método construtor sem parãmetros

    /**
     * Pega conexão com o banco de dados
     */
    private void takeConnection() {
        connectionDatabase = ConnectionDatabase.getInstance();
        connection = connectionDatabase.getConnection();
    }//fim do método takeConnection

    public void registerResponsible(Responsible responsible) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT cadastra_responsavel(?,?,?,?);");
            preparedStatement.setInt(1, responsible.getCodeStudent());
            preparedStatement.setString(2, responsible.getName());
            preparedStatement.setString(3, responsible.getRG());

            preparedStatement.setString(4, responsible.getPhone());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível adicionar responsavel!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível adicionar responsavel!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }

    public void editResponsible(Responsible responsible) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT edita_responsavel(?,?,?,?,?);");
            preparedStatement.setInt(1, responsible.getCodeResponsible());
            preparedStatement.setInt(2, responsible.getCodeStudent());
            preparedStatement.setString(3, responsible.getName());
            preparedStatement.setString(4, responsible.getRG());
            preparedStatement.setString(5, responsible.getPhone());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível editar responsavel!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível editar responsavel!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }

    public Responsible searchResponsible(Student student) {
        Responsible responsible = new Responsible();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT busca_responsavel.codigo_responsavel , busca_responsavel.codigo_aluno, busca_responsavel.nome, busca_responsavel.telefone, busca_responsavel.rg    FROM busca_responsavel(?);");
            preparedStatement.setInt(1, student.getCodeStudent());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                responsible.setCodeResponsible(resultSet.getInt(1));
                responsible.setCodeStudent(resultSet.getInt(2));
                responsible.setName(resultSet.getString(3));
                responsible.setPhone(resultSet.getString(4));
                responsible.setRG(resultSet.getString(5));
            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar responsavel pelo código do aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar responsavel!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return responsible;
    }
}//fim da classe ResponsibleDAO

