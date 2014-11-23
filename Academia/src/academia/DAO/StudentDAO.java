
/*-
 * Classname:             StudentDAO.java
 *
 * Version information:   (versão)
 *
 * Date:                  05/02/2013 - 21:09:17
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.DAO;

import academia.model.Student;
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
public class StudentDAO {
    //classe responsável pela conexão com o banco de dados

    private ConnectionDatabase connectionDatabase;
    //conexão com o banco
    private Connection connection;

    /**
     * Método construtor sem parâmetros
     */
    public StudentDAO() {
        takeConnection();
    }//fim do método construtor sem parãmetros

    /**
     * Pega conexão com o banco de dados
     */
    private void takeConnection() {
        connectionDatabase = ConnectionDatabase.getInstance();
        connection = connectionDatabase.getConnection();
    }//fim do método takeConnection

    public int registerStudent(Student student) {
        int codeStudent = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "SELECT * FROM cadastra_aluno( ?, ? , ? , ? , ? , ? , ? , ? , ?,  ?, ? , ? , ? , ?, ?, ? , ? );");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSex());
            preparedStatement.setString(3, student.getMaritalStatus());
            preparedStatement.setString(4, student.getNationality());
            preparedStatement.setString(5, student.getCity());
            preparedStatement.setString(6, student.getUF());
            preparedStatement.setString(7, student.getAddress());
            preparedStatement.setString(8, student.getDistrict());
            preparedStatement.setInt(9, student.getNumber());
            preparedStatement.setString(10, student.getCEP());
            preparedStatement.setString(11, student.getPhone());
            preparedStatement.setString(12, student.getCellPhone());
            preparedStatement.setString(13, student.getMail());
            preparedStatement.setDate(14, student.getDateOfBirth());
            preparedStatement.setString(15, student.getRG());
            preparedStatement.setString(16, student.getObjective());
            preparedStatement.setString(17, student.getObservation());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                codeStudent = resultSet.getInt(1);
            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível adicionar aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível adicionar aluno!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return codeStudent;
    }

    public void editStudent(Student student) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT edita_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, student.getCodeStudent());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setString(4, student.getMaritalStatus());
            preparedStatement.setString(5, student.getNationality());
            preparedStatement.setString(6, student.getCity());
            preparedStatement.setString(7, student.getUF());
            preparedStatement.setString(8, student.getAddress());
            preparedStatement.setString(9, student.getDistrict());
            preparedStatement.setInt(10, student.getNumber());
            preparedStatement.setString(11, student.getCEP());
            preparedStatement.setString(12, student.getPhone());
            preparedStatement.setString(13, student.getCellPhone());
            preparedStatement.setString(14, student.getMail());
            preparedStatement.setDate(15, student.getDateOfBirth());
            preparedStatement.setString(16, student.getRG());
            preparedStatement.setString(17, student.getObjective());
            preparedStatement.setString(18, student.getObservation());
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível atualizar aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível atualizar aluno!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
    }

    public void eraseStudent(Student student) {
        try {
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement("SELECT exclui_aluno(?);");

            preparedStatement.setInt(1, student.getCodeStudent());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível deletar aluno!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível apagar aluno!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }

    }

    public Iterator searchStudent() {
        List<Student> students = new ArrayList();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT * FROM ver_alunos;");
            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setCodeStudent(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setMaritalStatus(resultSet.getString(4));
                student.setNationality(resultSet.getString(5));
                student.setCity(resultSet.getString(6));
                student.setUF(resultSet.getString(7));
                student.setAddress(resultSet.getString(8));
                student.setDistrict(resultSet.getString(9));
                student.setNumber(resultSet.getInt(10));
                student.setCEP(resultSet.getString(11));
                student.setPhone(resultSet.getString(12));
                student.setCellPhone(resultSet.getString(13));
                student.setMail(resultSet.getString(14));
                student.setDateOfBirth(resultSet.getDate(15));
                student.setRG(resultSet.getString(16));
                student.setObjective(resultSet.getString(17));
                student.setObservation(resultSet.getString(18));
                students.add(student);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar alunos!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar alunos!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return students.iterator();
    }

    public Iterator searchStudentByName(String name) {

        List<Student> students = new ArrayList();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT codigo_aluno, nome, sexo, estado_civil, nacionalidade, cidade, uf, endereco, bairro, numero, cep, telefone, celular, mail, data_nascimento, objetivo, observacao, rg FROM busca_alunos_nome(?);");
            searcher.setString(1, name);
            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setCodeStudent(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setMaritalStatus(resultSet.getString(4));
                student.setNationality(resultSet.getString(5));
                student.setCity(resultSet.getString(6));
                student.setUF(resultSet.getString(7));
                student.setAddress(resultSet.getString(8));
                student.setDistrict(resultSet.getString(9));
                student.setNumber(resultSet.getInt(10));
                student.setCEP(resultSet.getString(11));
                student.setPhone(resultSet.getString(12));
                student.setCellPhone(resultSet.getString(13));
                student.setMail(resultSet.getString(14));
                student.setDateOfBirth(resultSet.getDate(15));
                student.setObjective(resultSet.getString(16));
                student.setObservation(resultSet.getString(17));
                student.setRG(resultSet.getString(18));
                students.add(student);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar alunos pelo nome!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar alunos pelo nome!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return students.iterator();
    }

    public Iterator searchStudentByRg(String rg) {

        List<Student> students = new ArrayList();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT * FROM busca_alunos_rg(?);");
            searcher.setString(1, rg);
            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setCodeStudent(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setMaritalStatus(resultSet.getString(4));
                student.setNationality(resultSet.getString(5));
                student.setCity(resultSet.getString(6));
                student.setUF(resultSet.getString(7));
                student.setAddress(resultSet.getString(8));
                student.setDistrict(resultSet.getString(9));
                student.setNumber(resultSet.getInt(10));
                student.setCEP(resultSet.getString(11));
                student.setPhone(resultSet.getString(12));
                student.setCellPhone(resultSet.getString(13));
                student.setMail(resultSet.getString(14));
                student.setDateOfBirth(resultSet.getDate(15));
                student.setRG(resultSet.getString(16));
                student.setObjective(resultSet.getString(17));
                student.setObservation(resultSet.getString(18));
                students.add(student);
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar alunos pelo RG!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar alunos pelo RG!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return students.iterator();
    }

    public Student searchStudentForCode(int codeStudent) {

        Student student = new Student();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT * FROM busca_alunos_codigo(?);");
            searcher.setInt(1, codeStudent);
            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {
                student.setCodeStudent(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setSex(resultSet.getString(3));
                student.setMaritalStatus(resultSet.getString(4));
                student.setNationality(resultSet.getString(5));
                student.setCity(resultSet.getString(6));
                student.setUF(resultSet.getString(7));
                student.setAddress(resultSet.getString(8));
                student.setDistrict(resultSet.getString(9));
                student.setNumber(resultSet.getInt(10));
                student.setCEP(resultSet.getString(11));
                student.setPhone(resultSet.getString(12));
                student.setCellPhone(resultSet.getString(13));
                student.setMail(resultSet.getString(14));
                student.setDateOfBirth(resultSet.getDate(15));
                student.setRG(resultSet.getString(16));
                student.setObjective(resultSet.getString(17));
                student.setObservation(resultSet.getString(18));
            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar aluno pelo código!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar aluno pelo código!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return student;
    }

    public List<String> searchNascionalityList() {

        List<String> nascionalityList = new ArrayList();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT * FROM  lista_nacionalidade();");

            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {
                nascionalityList.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar nascionalidades!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar nascionalidades!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return nascionalityList;
    }

    public List<String> searchMaritalStatusList() {

        List<String> maritalStatusList = new ArrayList();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT * FROM  lista_estado_civil();");

            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {

                maritalStatusList.add(resultSet.getString(1));

            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar estados cívi!l");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar estados civil!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return maritalStatusList;
    }

    public List<String> searchCityList() {

        List<String> cityList = new ArrayList();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT * FROM  lista_cidade();");

            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {

                cityList.add(resultSet.getString(1));

            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar cidades!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar cidades!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return cityList;
    }

    public List<String> searchDistrictLisList() {

        List<String> districtList = new ArrayList();
        try {
            PreparedStatement searcher = connection.prepareStatement("SELECT * FROM  lista_bairro();;");

            ResultSet resultSet = searcher.executeQuery();
            while (resultSet.next()) {

                districtList.add(resultSet.getString(1));

            }

        } catch (SQLException sqlException) {
            System.err.println("Erro: não foi possível buscar bairros!");
            sqlException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: não foi possível buscar bairros!", "Erro!", JOptionPane.WARNING_MESSAGE);
            LogMaker.create(sqlException);
        }
        return districtList;
    }
}//fim da classe StudentDAO

