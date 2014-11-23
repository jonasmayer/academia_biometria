
/*-
 * Classname:             PointDAO.java
 *
 * Version information:   (versão)
 *
 * Date:                  08/02/2013 - 17:16:43
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.DAO;

import academia.model.Point;
import academia.util.LogMaker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class PointDAO {

    //classe responsável pela conexão com o banco de dados
    private ConnectionDatabase connectionDatabase;
    //conexão com o banco
    private Connection connection;

    /**
     * Método construtor sem parâmetros
     */
    public PointDAO() {
        takeConnection();
    }//fim do método construtor sem parãmetros

    /**
     * Pega conexão com o banco de dados
     */
    private void takeConnection() {
        connectionDatabase = ConnectionDatabase.getInstance();
        connection = connectionDatabase.getConnection();
    }//fim do método takeConnection

    public int addPoint(Point point) {
        int codePoint = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adiciona_ponto(?,?,?,?);");
            preparedStatement.setTime(1, point.getEntry());
            preparedStatement.setTime(2, point.getExit());
            preparedStatement.setDate(3, point.getDay());
            preparedStatement.setInt(4, point.getCodeStudent());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                codePoint = resultSet.getInt(1);
            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível adicionar ponto!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível adicionar ponto!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(sqlException);
        }
        return codePoint;
    }

    public void editPoint(Point point) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT edita_ponto(?,?,?,?,?);");
            preparedStatement.setInt(1, point.getCodePoint());
            preparedStatement.setTime(2, point.getEntry());
            preparedStatement.setTime(3, point.getExit());
            preparedStatement.setDate(4, point.getDay());
            preparedStatement.setInt(5, point.getCodeStudent());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível atualizar ponto!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível atualizar ponto!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }

    public void erasePoint(Point point) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT exclui_ponto(?);");
            preparedStatement.setInt(1, point.getCodePoint());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível excluit  ponto!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível excluir ponto!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }

    public Iterator searchPointForStudent(int codeStudent) {
        List<Point> points = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT codigo_ponto, entrada,saida,data_ponto,codigo_aluno   FROM listar_ponto_aluno(?);");
            preparedStatement.setInt(1, codeStudent);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Point point = new Point();
                point.setCodePoint(resultSet.getInt(1));
                point.setEntry(resultSet.getTime(2));
                point.setExit(resultSet.getTime(3));
                point.setDay(resultSet.getDate(4));
                point.setCodeStudent(resultSet.getInt(5));
                points.add(point);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar pontos de aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar ponto!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return points.iterator();
    }

    public Iterator searchPoints() {
        List<Point> points = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT codigo_ponto, entrada,saida,data_ponto,codigo_aluno   FROM lista_ponto();");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Point point = new Point();
                point.setCodePoint(resultSet.getInt(1));
                point.setEntry(resultSet.getTime(2));
                point.setExit(resultSet.getTime(3));
                point.setDay(resultSet.getDate(4));
                point.setCodeStudent(resultSet.getInt(5));
                points.add(point);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar pontos de aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar ponto!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return points.iterator();
    }

    public Iterator searchOpenPoint() {
        List<Point> points = new ArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT codigo_ponto, entrada, saida, data_ponto, codigo_aluno  FROM lista_ponto_aberto(?);");
            preparedStatement.setDate(1, new java.sql.Date(System.currentTimeMillis()));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Point point = new Point();
                point.setCodePoint(resultSet.getInt(1));
                point.setEntry(resultSet.getTime(2));
                point.setExit(resultSet.getTime(3));
                point.setDay(resultSet.getDate(4));
                point.setCodeStudent(resultSet.getInt(5));
                points.add(point);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar pontos de aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar ponto!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return points.iterator();
    }

    public Point searchOpenPoint(int codeStudent) {
        Point point = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT codigo_ponto, entrada, saida,data_ponto, codigo_aluno FROM lista_ponto_aberto_aluno(?,?);");
            preparedStatement.setInt(1, codeStudent);
            preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                point = new Point();
                point.setCodePoint(resultSet.getInt(1));
                point.setEntry(resultSet.getTime(2));
                point.setExit(resultSet.getTime(3));
                point.setDay(resultSet.getDate(4));
                point.setCodeStudent(resultSet.getInt(5));
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar pontos de aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar ponto!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return point;
    }

    public Point searchLastPoint(int codeStudent) {
        Point point = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT codigo_ponto, entrada, saida,data_ponto, codigo_aluno FROM lista_ponto_ultimo_aluno(?,?);");
            preparedStatement.setInt(1, codeStudent);
            preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                point = new Point();
                point.setCodePoint(resultSet.getInt(1));
                point.setEntry(resultSet.getTime(2));
                point.setExit(resultSet.getTime(3));
                point.setDay(resultSet.getDate(4));
                point.setCodeStudent(resultSet.getInt(5));
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar ultimo pontos de aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar ultimo ponto!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return point;
    }
}//fim da classe PointDAO

