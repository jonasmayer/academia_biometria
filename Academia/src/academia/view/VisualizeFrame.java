
/*-
 * Classname:             VisualizeFrame.java
 *
 * Version information:   (versão)
 *
 * Date:                  18/02/2013 - 18:29:03
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.EmergencyContact;
import academia.model.Responsible;
import academia.model.Student;
import academia.util.FontFactory;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class VisualizeFrame {

    private JFrame frame;
    private JTextArea textArea;
    private int x;
    private int y;
    private String title;
    private Student student;
    private EmergencyContact emergencyContact;
    private Responsible responsible;

    public VisualizeFrame(Student student, EmergencyContact emergencyContact) {
        this.student = student;
        this.emergencyContact = emergencyContact;
        title = "Visualizar " + student.getName();
        createView();
        fillsView();
    }

    public VisualizeFrame(Student student, EmergencyContact emergencyContact, Responsible responsible) {
        this.student = student;
        this.emergencyContact = emergencyContact;
        this.responsible = responsible;
        title = "Visualizar " + student.getName();
        createView();
        fillsView();
    }

    private void createView() {
        frame = new JFrame(title);
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.setLayout(null);
        textArea.setEditable(false);

        takeScreenSize();
        frame.setBounds((x - 500) / 2, (y - 350) / 2, 500, 350);
        scrollPane.setBounds(10, 10, 460, 300);

        textArea.setFont(FontFactory.getFontDefault());

        textArea.setBackground(Color.WHITE);

        scrollPane.setViewportView(textArea);
        frame.add(scrollPane);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                frame.dispose();
            }
        });
    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    private void fillsView() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormated = dateFormat.format(student.getDateOfBirth());
        textArea.append(" Nome: " + student.getName() + "\n");
        textArea.append(" Sexo: " + student.getSex() + "\n");
        textArea.append(" Estado cívil: " + student.getMaritalStatus() + "\n");
        textArea.append(" Data de Nascimento: " + dateFormated + "\n");
        textArea.append(" Idade: " + student.getAge() + "\n");
        textArea.append(" RG: " + student.getRG() + "\n");
        textArea.append(" Nascionalidade: " + student.getNationality() + "\n");
        textArea.append(" UF: " + student.getUF() + "\n");
        textArea.append(" CEP: " + student.getCEP() + "\n");
        textArea.append(" Cidade: " + student.getCity() + "\n");
        textArea.append(" Endereço: " + student.getAddress() + "\n");
        textArea.append(" Bairro: " + student.getDistrict() + "\n");
        textArea.append(" Número: " + student.getNumber() + "\n");
        textArea.append("-Contato" + "\n");
        textArea.append(" Telefone: " + student.getPhone() + "\n");
        textArea.append(" Celular: " + student.getCellPhone() + "\n");
        textArea.append(" Email: " + student.getMail() + "\n");
        textArea.append(" Objetivo: " + student.getObjective() + "\n");
        textArea.append(" Observação: " + student.getObservation() + "\n");
        if (responsible != null) {
            textArea.append("\n");
            textArea.append("-Responsável" + "\n");
            textArea.append(" Nome: " + responsible.getName() + "\n");
            textArea.append(" RG: " + responsible.getRG() + "\n");
            textArea.append(" Telefone: " + responsible.getPhone() + "\n");
        }
        textArea.append("\n");
        textArea.append("-Contato de Emergência" + "\n");
        textArea.append(" Nome: " + emergencyContact.getName() + "\n");
        textArea.append(" Telefone: " + emergencyContact.getPhone() + "\n");
        textArea.append(" Cidade: " + emergencyContact.getCity() + "\n");
        textArea.append(" UF: " + emergencyContact.getUF() + "\n");
        textArea.append(" Grau de Parentesco: " + emergencyContact.getRelationship() + "\n");

        textArea.setCaretPosition(0);

    }

    public static void main(String args[]) {
        VisualizeFrame visualizeFrame = new VisualizeFrame(new Student("name", "sex", "maritalStatus",
                "nationality", "city", "uf", "address", "district", 0, "CEP", "phone",
                "cellPhone", "mail", new Date(1000000), "0", "objective",
                "observation"), new EmergencyContact(0,
                "name",
                "phone",
                "city",
                "uf",
                "relationship"));
    }
}//fim da classe VisualizeFrame

