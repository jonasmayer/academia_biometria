
/*-
 * Classname:             AddStudentFrame.java
 *
 * Version information:   (versão)
 *
 * Date:                  19/02/2013 - 17:21:45
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.view;

import academia.model.EmergencyContact;
import academia.model.Responsible;
import academia.model.Student;
import academia.util.DateDocumentFilter;
import academia.util.FontFactory;
import academia.util.JAutoSuggestComboBox;
import academia.util.JContextPopupMenu;
import academia.util.LogMaker;
import academia.util.NumberDocumentFilter;
import academia.util.ScreenResolution;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class AddStudentFrame {

    //estudante
    private Student student;
    //contato para emergência 
    private EmergencyContact emergencyContact;
    //responsável
    private Responsible responsible;
    //componentes
    private JFrame frame;
    private String title;
    private JScrollPane scrollPane;
    //etapas do cadastro
    //dados pessoais
    private JPanel personalDataPanel;
    //endereço
    private JPanel adressPanel;
    //contato
    private JPanel contactPanel;
    //observação
    private JPanel observationPanel;
    //responsável
    private JPanel responsiblePanel;
    //contato para emergência
    private JPanel emergencyContactPanel;
    //digital
    private JPanel fingerprintPanel;
    //resolução da tela
    private int x;
    private int y;
    //contador de etapa de cadastro
    private int countPanel;
    private int age;
    //componentes do cadastro
    private JLabel personalDataTitle;
    private JLabel adressTitle;
    private JLabel contactTitle;
    private JLabel observationTitle;
    private JLabel responsibleTitle;
    private JLabel emergencyContactTitle;
    private JLabel fingerprintTitle;
    private JLabel nameLabel;
    private JLabel sexLabel;
    private JLabel maritalStatusLabel;
    private JLabel dateBirthLabel;
    private JLabel ageLabel;
    private JLabel rgLabel;
    private JLabel nascionalityLabel;
    private JLabel cityLabel;
    private JLabel ufLabel;
    private JLabel adressLabel;
    private JLabel districtLabel;
    private JLabel numberLabel;
    private JLabel CEPLabel;
    private JLabel phoneLabel;
    private JLabel cellPhoneLabel;
    private JLabel mailLabel;
    private JLabel objectiveLabel;
    private JLabel observationLabel;
    private JLabel relationshipLabel;
    private JLabel maleLabel;
    private JLabel femaleLabel;
    private JLabel nameResponsibleLabel;
    private JLabel rgResponsibleLabel;
    private JLabel phoneResponsibleLabel;
    private JLabel nameEmergencyContactLabel;
    private JLabel cityEmergencyContactLabel;
    private JLabel ufEmergencyContactLabel;
    private JLabel phoneEmergencyContactLabel;
    //
    private JTextField nameTextField;
    private JAutoSuggestComboBox maritalStatusTextField;
    private JTextField dateBirthTextField;
    private JTextField ageTextField;
    private JTextField rgTextField;
    private JAutoSuggestComboBox nascionalityTextField;
    private JAutoSuggestComboBox cityTextField;
    private JTextField adressTextField;
    private JAutoSuggestComboBox districtTextField;
    private JTextField numberTextField;
    private JTextField cepTextField;
    private JTextField phoneTextField;
    private JTextField cellphoneTextField;
    private JTextField mailTextField;
    private JTextArea observationTextField;
    private JTextField nameResponsibleTextField;
    private JTextField rgResponsibleTextField;
    private JTextField phoneResponsibleTextField;
    private JTextField nameEmergencyContactTextField;
    private JAutoSuggestComboBox cityEmergencyContactTextField;
    private JTextField phoneEmergencyContactTextField;
    private JTextField relationshipTextField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton nextButton;
    private JButton previousButton;
    private JButton okButton;
    private JButton scanButton;
    private JComboBox ufComboBox;
    private JComboBox ufEmergencyContactComboBox;
    private JComboBox objectiveComboBox;
    private Border greenBorder;
    private Border redBorder;
    private Border emptyBorder;
    private HandsPanel handsPanel;

    public AddStudentFrame() {
        student = new Student();
        title = "Adicionar aluno";
        createView();
    }

    public AddStudentFrame(Student student, EmergencyContact emergencyContact) {
        this.student = student;
        this.emergencyContact = emergencyContact;
        title = "Editar aluno";
        createView();
        fillsView();
    }

    public AddStudentFrame(Student student, EmergencyContact emergencyContact, Responsible responsible) {
        this.student = student;
        this.emergencyContact = emergencyContact;
        this.responsible = responsible;
        title = "Editar aluno";
        createView();
        fillsView();
    }

    private void createView() {
        //inicializa componentes
        frame = new JFrame(title);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        personalDataPanel = new JPanel();
        adressPanel = new JPanel();
        contactPanel = new JPanel();
        observationPanel = new JPanel();
        responsiblePanel = new JPanel();
        emergencyContactPanel = new JPanel();
        fingerprintPanel = new JPanel();
        greenBorder = BorderFactory.createLineBorder(new Color(00, 210, 00), 2);
        redBorder = BorderFactory.createLineBorder(Color.RED, 2);
        emptyBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);

        personalDataTitle = new JLabel("Dados Pessoais");
        adressTitle = new JLabel("Endereço");
        contactTitle = new JLabel("Contato");
        observationTitle = new JLabel("Observações");
        responsibleTitle = new JLabel("Responsável");
        emergencyContactTitle = new JLabel("Contato para Emergência");
        nameLabel = new JLabel("Nome:");
        sexLabel = new JLabel("Sexo:");
        maritalStatusLabel = new JLabel("Estado Cívil:");
        dateBirthLabel = new JLabel("Data de nascimento:");
        ageLabel = new JLabel("Idade:");
        rgLabel = new JLabel("RG:");
        nascionalityLabel = new JLabel("Nascionalidade:");
        cityLabel = new JLabel("Cidade:");
        ufLabel = new JLabel("UF:");
        adressLabel = new JLabel("Endereço:");
        districtLabel = new JLabel("Bairro:");
        numberLabel = new JLabel("Número:");
        CEPLabel = new JLabel("CEP:");
        phoneLabel = new JLabel("Telefone:");
        cellPhoneLabel = new JLabel("Celular:");
        mailLabel = new JLabel("Email:");
        objectiveLabel = new JLabel("Objetivo:");
        observationLabel = new JLabel("Observação:");
        relationshipLabel = new JLabel("Grau de Parentesco:");
        maleLabel = new JLabel("Maculino");
        femaleLabel = new JLabel("Feminino");
        nameResponsibleLabel = new JLabel("Nome:");
        rgResponsibleLabel = new JLabel("RG:");
        phoneResponsibleLabel = new JLabel("Telefone:");
        nameEmergencyContactLabel = new JLabel("Nome:");
        cityEmergencyContactLabel = new JLabel("Cidade:");
        ufEmergencyContactLabel = new JLabel("UF:");
        phoneEmergencyContactLabel = new JLabel("Telefone:");
        fingerprintTitle = new JLabel("Impressão Digital");
        nameTextField = new JTextField();
        maritalStatusTextField = new JAutoSuggestComboBox();
        dateBirthTextField = new JTextField();
        ageTextField = new JTextField();
        rgTextField = new JTextField();
        nascionalityTextField = new JAutoSuggestComboBox();
        cityTextField = new JAutoSuggestComboBox();
        adressTextField = new JTextField();
        districtTextField = new JAutoSuggestComboBox();
        numberTextField = new JTextField();
        cepTextField = new JTextField();
        phoneTextField = new JTextField();
        cellphoneTextField = new JTextField();
        mailTextField = new JTextField();
        observationTextField = new JTextArea();
        nameResponsibleTextField = new JTextField();
        rgResponsibleTextField = new JTextField();
        phoneResponsibleTextField = new JTextField();
        nameEmergencyContactTextField = new JTextField();
        cityEmergencyContactTextField = new JAutoSuggestComboBox();

        phoneEmergencyContactTextField = new JTextField();
        relationshipTextField = new JTextField();
        maleRadioButton = new JRadioButton();
        femaleRadioButton = new JRadioButton();
        nextButton = new JButton("Próximo >");
        previousButton = new JButton("Anterior");
        okButton = new JButton("Finalizar");
        scanButton = new JButton("Escanear");
        ufComboBox = new JComboBox(new Object[]{"AC", "AL", "AM", "AP", "BA", "CE", "DF",
            "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR",
            "RS", "SC", "SE", "SP", "TO"});
        ufEmergencyContactComboBox = new JComboBox(new Object[]{"AC", "AL", "AM", "AP", "BA", "CE", "DF",
            "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR",
            "RS", "SC", "SE", "SP", "TO"});
        objectiveComboBox = new JComboBox(new Object[]{" ", "Condicionamento Físico", "Prep. Especial p/ Esportes", "Convívio Social", "Combate ao Stress", "Saúde", "Terapêutica", "Lazer", "Estética"});

        handsPanel = new HandsPanel();
        //define tamanho e localização
        personalDataPanel.setLayout(null);
        adressPanel.setLayout(null);
        contactPanel.setLayout(null);
        observationPanel.setLayout(null);
        responsiblePanel.setLayout(null);
        emergencyContactPanel.setLayout(null);
        fingerprintPanel.setLayout(null);
        handsPanel.setLayout(null);

        frame.setLayout(null);
        ageTextField.setEditable(false);
        ageTextField.setFocusable(false);
        observationTextField.setLineWrap(true);
        observationTextField.setWrapStyleWord(true);
        DocumentFilter numberDocumentFilter = new NumberDocumentFilter();
        ((AbstractDocument) rgTextField.getDocument()).setDocumentFilter(numberDocumentFilter);
        ((AbstractDocument) numberTextField.getDocument()).setDocumentFilter(numberDocumentFilter);

        maritalStatusTextField.setEditable(true);
        nascionalityTextField.setEditable(true);
        cityTextField.setEditable(true);
        districtTextField.setEditable(true);
        cityEmergencyContactTextField.setEditable(true);

        takeScreenSize();
        frame.setBounds((x - (x - 200)) / 2, (y - (y - 100)) / 2, x - 200, y - 100);
        scrollPane.setBounds(10, 10, x - 250, y - 225);

        nextButton.setBounds(x - 400, y - 200, 150, 30);
        previousButton.setBounds(x - 600, y - 200, 150, 30);
        okButton.setBounds(x - 400, y - 200, 150, 30);

        // personalDataPanel
        personalDataTitle.setBounds((x - 400) / 2, 2, 150, 30);
        nameLabel.setBounds(10, 40, 100, 30);
        sexLabel.setBounds(10, 80, 100, 30);
        maritalStatusLabel.setBounds(10, 120, 100, 30);
        dateBirthLabel.setBounds(10, 160, 150, 30);
        ageLabel.setBounds(400, 160, 50, 30);
        rgLabel.setBounds(10, 200, 100, 30);
        nascionalityLabel.setBounds(10, 240, 150, 30);
        maleLabel.setBounds(190, 80, 75, 30);
        femaleLabel.setBounds(330, 80, 75, 30);
        nameTextField.setBounds(160, 40, 250, 30);
        maritalStatusTextField.setBounds(160, 120, 150, 30);
        dateBirthTextField.setBounds(160, 160, 100, 30);
        ageTextField.setBounds(450, 160, 50, 30);
        ageTextField.setHorizontalAlignment(JTextField.CENTER);
        rgTextField.setBounds(160, 200, 150, 30);
        maleRadioButton.setBounds(160, 80, 30, 30);
        femaleRadioButton.setBounds(300, 80, 30, 30);
        nascionalityTextField.setBounds(160, 240, 150, 30);

        // adressPanel
        adressTitle.setBounds((x - 400) / 2, 2, 150, 30);
        cityLabel.setBounds(10, 40, 150, 30);
        ufLabel.setBounds(10, 80, 100, 30);
        adressLabel.setBounds(10, 120, 150, 30);
        districtLabel.setBounds(10, 160, 150, 30);
        numberLabel.setBounds(10, 200, 150, 30);
        CEPLabel.setBounds(10, 240, 150, 30);
        cityTextField.setBounds(160, 40, 150, 30);
        adressTextField.setBounds(160, 120, 200, 30);
        districtTextField.setBounds(160, 160, 150, 30);
        numberTextField.setBounds(160, 200, 75, 30);
        cepTextField.setBounds(160, 240, 150, 30);
        ufComboBox.setBounds(160, 80, 100, 30);

        // contactPanel
        contactTitle.setBounds((x - 400) / 2, 2, 150, 30);
        phoneLabel.setBounds(10, 40, 150, 30);
        cellPhoneLabel.setBounds(10, 80, 150, 30);
        mailLabel.setBounds(10, 120, 150, 30);
        phoneTextField.setBounds(160, 40, 150, 30);
        cellphoneTextField.setBounds(160, 80, 150, 30);
        mailTextField.setBounds(160, 120, 200, 30);

        // observationPanel
        observationTitle.setBounds((x - 400) / 2, 2, 150, 30);
        objectiveLabel.setBounds(10, 40, 150, 30);
        observationLabel.setBounds(10, 90, 150, 30);
        observationTextField.setBounds(160, 90, 200, 100);
        objectiveComboBox.setBounds(160, 40, 200, 30);

        // responsiblePanel
        responsibleTitle.setBounds((x - 400) / 2, 2, 150, 30);
        nameResponsibleLabel.setBounds(10, 40, 150, 30);
        rgResponsibleLabel.setBounds(10, 80, 150, 30);
        phoneResponsibleLabel.setBounds(10, 120, 150, 30);
        nameResponsibleTextField.setBounds(160, 40, 150, 30);
        rgResponsibleTextField.setBounds(160, 80, 150, 30);
        phoneResponsibleTextField.setBounds(160, 120, 150, 30);

        // emergencyContactPanel
        emergencyContactTitle.setBounds((x - 500) / 2, 2, 250, 30);
        nameEmergencyContactLabel.setBounds(10, 40, 150, 30);
        cityEmergencyContactLabel.setBounds(10, 80, 150, 30);
        ufEmergencyContactLabel.setBounds(10, 120, 150, 30);
        phoneEmergencyContactLabel.setBounds(10, 160, 150, 30);
        relationshipLabel.setBounds(10, 200, 150, 30);
        nameEmergencyContactTextField.setBounds(160, 40, 200, 30);
        cityEmergencyContactTextField.setBounds(160, 80, 150, 30);
        ufEmergencyContactComboBox.setBounds(160, 120, 150, 30);
        phoneEmergencyContactTextField.setBounds(160, 160, 150, 30);
        relationshipTextField.setBounds(160, 200, 150, 30);

        // fingerprintPanel
        fingerprintTitle.setBounds((x - 500) / 2, 2, 250, 30);
        scanButton.setBounds(x - 550, 250, 200, 30);
        handsPanel.setBounds(100, (y - 550) / 2, 450, 320);

        //configurações visuais
        nextButton.setFont(FontFactory.getFontLarge());
        previousButton.setFont(FontFactory.getFontLarge());
        okButton.setFont(FontFactory.getFontLarge());

        // personalDataPanel
        personalDataTitle.setFont(FontFactory.getFontLarge());
        nameLabel.setFont(FontFactory.getFontDefault());
        sexLabel.setFont(FontFactory.getFontDefault());
        maritalStatusLabel.setFont(FontFactory.getFontDefault());
        dateBirthLabel.setFont(FontFactory.getFontDefault());
        ageLabel.setFont(FontFactory.getFontDefault());
        rgLabel.setFont(FontFactory.getFontDefault());
        maleLabel.setFont(FontFactory.getFontDefault());
        femaleLabel.setFont(FontFactory.getFontDefault());
        nameTextField.setFont(FontFactory.getFontDefault());
        maritalStatusTextField.setFont(FontFactory.getFontDefault());
        dateBirthTextField.setFont(FontFactory.getFontDefault());
        ageTextField.setFont(FontFactory.getFontDefault());
        rgTextField.setFont(FontFactory.getFontDefault());
        maleRadioButton.setFont(FontFactory.getFontDefault());
        femaleRadioButton.setFont(FontFactory.getFontDefault());
        nascionalityLabel.setFont(FontFactory.getFontDefault());
        nascionalityTextField.setFont(FontFactory.getFontDefault());

        // adressPanel
        adressTitle.setFont(FontFactory.getFontLarge());
        cityLabel.setFont(FontFactory.getFontDefault());
        ufLabel.setFont(FontFactory.getFontDefault());
        adressLabel.setFont(FontFactory.getFontDefault());
        districtLabel.setFont(FontFactory.getFontDefault());
        numberLabel.setFont(FontFactory.getFontDefault());
        CEPLabel.setFont(FontFactory.getFontDefault());
        cityTextField.setFont(FontFactory.getFontDefault());
        adressTextField.setFont(FontFactory.getFontDefault());
        districtTextField.setFont(FontFactory.getFontDefault());
        numberTextField.setFont(FontFactory.getFontDefault());
        cepTextField.setFont(FontFactory.getFontDefault());
        ufComboBox.setFont(FontFactory.getFontDefault());

        // contactPanel
        contactTitle.setFont(FontFactory.getFontLarge());
        phoneLabel.setFont(FontFactory.getFontDefault());
        cellPhoneLabel.setFont(FontFactory.getFontDefault());
        mailLabel.setFont(FontFactory.getFontDefault());
        phoneTextField.setFont(FontFactory.getFontDefault());
        cellphoneTextField.setFont(FontFactory.getFontDefault());
        mailTextField.setFont(FontFactory.getFontDefault());

        // observationPanel
        observationTitle.setFont(FontFactory.getFontLarge());
        objectiveLabel.setFont(FontFactory.getFontDefault());
        observationLabel.setFont(FontFactory.getFontDefault());
        observationTextField.setFont(FontFactory.getFontDefault());
        objectiveComboBox.setFont(FontFactory.getFontDefault());

        // responsiblePanel
        responsibleTitle.setFont(FontFactory.getFontLarge());
        nameResponsibleLabel.setFont(FontFactory.getFontDefault());
        rgResponsibleLabel.setFont(FontFactory.getFontDefault());
        phoneResponsibleLabel.setFont(FontFactory.getFontDefault());
        nameResponsibleTextField.setFont(FontFactory.getFontDefault());
        rgResponsibleTextField.setFont(FontFactory.getFontDefault());
        phoneResponsibleTextField.setFont(FontFactory.getFontDefault());

        // fingerprintPanel
        scanButton.setFont(FontFactory.getFontLarge());
        fingerprintTitle.setFont(FontFactory.getFontLarge());
        fingerprintTitle.setHorizontalAlignment(JTextField.CENTER);

        // emergencyContactPanel
        emergencyContactTitle.setFont(FontFactory.getFontLarge());
        nameEmergencyContactLabel.setFont(FontFactory.getFontDefault());
        cityEmergencyContactLabel.setFont(FontFactory.getFontDefault());
        ufEmergencyContactLabel.setFont(FontFactory.getFontDefault());
        phoneEmergencyContactLabel.setFont(FontFactory.getFontDefault());
        relationshipLabel.setFont(FontFactory.getFontDefault());
        nameEmergencyContactTextField.setFont(FontFactory.getFontDefault());
        cityEmergencyContactTextField.setFont(FontFactory.getFontDefault());
        ufEmergencyContactComboBox.setFont(FontFactory.getFontDefault());
        phoneEmergencyContactTextField.setFont(FontFactory.getFontDefault());
        relationshipTextField.setFont(FontFactory.getFontDefault());

        //cor
        nextButton.setForeground(Color.BLACK);
        previousButton.setForeground(Color.BLACK);
        okButton.setForeground(Color.BLACK);
        personalDataTitle.setBackground(Color.BLACK);
        adressTitle.setBackground(Color.BLACK);
        contactTitle.setBackground(Color.BLACK);
        observationTitle.setBackground(Color.BLACK);
        responsibleTitle.setBackground(Color.BLACK);
        emergencyContactTitle.setBackground(Color.BLACK);

        // personalDataPanel
        nameLabel.setBackground(Color.BLACK);
        sexLabel.setBackground(Color.BLACK);
        maritalStatusLabel.setBackground(Color.BLACK);
        dateBirthLabel.setBackground(Color.BLACK);
        ageLabel.setBackground(Color.BLACK);
        rgLabel.setBackground(Color.BLACK);
        maleLabel.setBackground(Color.BLACK);
        femaleLabel.setBackground(Color.BLACK);
        nameTextField.setForeground(Color.BLACK);
        maritalStatusTextField.setForeground(Color.BLACK);
        dateBirthTextField.setForeground(Color.BLACK);
        ageTextField.setForeground(Color.BLACK);
        rgTextField.setForeground(Color.BLACK);
        maleRadioButton.setForeground(Color.BLACK);
        femaleRadioButton.setForeground(Color.BLACK);
        nascionalityLabel.setBackground(Color.BLACK);
        nascionalityTextField.setForeground(Color.BLACK);

        // adressPanel 
        cityLabel.setBackground(Color.BLACK);
        ufLabel.setBackground(Color.BLACK);
        adressLabel.setBackground(Color.BLACK);
        districtLabel.setBackground(Color.BLACK);
        numberLabel.setBackground(Color.BLACK);
        CEPLabel.setBackground(Color.BLACK);
        cityTextField.setForeground(Color.BLACK);
        adressTextField.setForeground(Color.BLACK);
        districtTextField.setForeground(Color.BLACK);
        numberTextField.setForeground(Color.BLACK);
        cepTextField.setForeground(Color.BLACK);
        ufComboBox.setForeground(Color.BLACK);

        // contactPanel 
        phoneLabel.setBackground(Color.BLACK);
        cellPhoneLabel.setBackground(Color.BLACK);
        mailLabel.setBackground(Color.BLACK);
        phoneTextField.setForeground(Color.BLACK);
        cellphoneTextField.setForeground(Color.BLACK);
        mailTextField.setForeground(Color.BLACK);

        // observationPanel 
        objectiveLabel.setBackground(Color.BLACK);
        observationLabel.setBackground(Color.BLACK);
        observationTextField.setForeground(Color.BLACK);
        objectiveComboBox.setForeground(Color.BLACK);

        // responsiblePanel 
        nameResponsibleLabel.setBackground(Color.BLACK);
        rgResponsibleLabel.setBackground(Color.BLACK);
        phoneResponsibleLabel.setBackground(Color.BLACK);
        nameResponsibleTextField.setForeground(Color.BLACK);
        rgResponsibleTextField.setForeground(Color.BLACK);
        phoneResponsibleTextField.setForeground(Color.BLACK);

        // emergencyContactPanel
        nameEmergencyContactLabel.setBackground(Color.BLACK);
        cityEmergencyContactLabel.setBackground(Color.BLACK);
        ufEmergencyContactLabel.setBackground(Color.BLACK);
        phoneEmergencyContactLabel.setBackground(Color.BLACK);
        relationshipLabel.setBackground(Color.BLACK);
        nameEmergencyContactTextField.setForeground(Color.BLACK);
        cityEmergencyContactTextField.setForeground(Color.BLACK);
        ufEmergencyContactComboBox.setForeground(Color.BLACK);
        phoneEmergencyContactTextField.setForeground(Color.BLACK);
        relationshipTextField.setForeground(Color.BLACK);

        // fingerprintPanel
        scanButton.setForeground(Color.BLACK);
        fingerprintTitle.setForeground(Color.BLACK);

        //adiciona componentes
        frame.add(nextButton);

        // personalDataPanel
        personalDataPanel.add(personalDataTitle);
        personalDataPanel.add(nameLabel);
        personalDataPanel.add(sexLabel);
        personalDataPanel.add(maritalStatusLabel);
        personalDataPanel.add(dateBirthLabel);
        personalDataPanel.add(ageLabel);
        personalDataPanel.add(rgLabel);
        personalDataPanel.add(maleLabel);
        personalDataPanel.add(femaleLabel);
        personalDataPanel.add(nameTextField);
        personalDataPanel.add(maritalStatusTextField);
        personalDataPanel.add(dateBirthTextField);
        personalDataPanel.add(ageTextField);
        personalDataPanel.add(rgTextField);
        personalDataPanel.add(maleRadioButton);
        personalDataPanel.add(femaleRadioButton);
        personalDataPanel.add(nascionalityLabel);
        personalDataPanel.add(nascionalityTextField);

        // adressPanel
        adressPanel.add(adressTitle);
        adressPanel.add(cityLabel);
        adressPanel.add(ufLabel);
        adressPanel.add(adressLabel);
        adressPanel.add(districtLabel);
        adressPanel.add(numberLabel);
        adressPanel.add(CEPLabel);
        adressPanel.add(cityTextField);
        adressPanel.add(adressTextField);
        adressPanel.add(districtTextField);
        adressPanel.add(numberTextField);
        adressPanel.add(cepTextField);
        adressPanel.add(ufComboBox);

        // contactPanel
        contactPanel.add(contactTitle);
        contactPanel.add(phoneLabel);
        contactPanel.add(cellPhoneLabel);
        contactPanel.add(mailLabel);
        contactPanel.add(phoneTextField);
        contactPanel.add(cellphoneTextField);
        contactPanel.add(mailTextField);

        // observationPanel
        observationPanel.add(observationTitle);
        observationPanel.add(objectiveLabel);
        observationPanel.add(observationLabel);
        observationPanel.add(observationTextField);
        observationPanel.add(objectiveComboBox);

        // responsiblePanel
        responsiblePanel.add(responsibleTitle);
        responsiblePanel.add(nameResponsibleLabel);
        responsiblePanel.add(rgResponsibleLabel);
        responsiblePanel.add(phoneResponsibleLabel);
        responsiblePanel.add(nameResponsibleTextField);
        responsiblePanel.add(rgResponsibleTextField);
        responsiblePanel.add(phoneResponsibleTextField);

        // emergencyContactPanel
        emergencyContactPanel.add(emergencyContactTitle);
        emergencyContactPanel.add(nameEmergencyContactLabel);
        emergencyContactPanel.add(cityEmergencyContactLabel);
        emergencyContactPanel.add(ufEmergencyContactLabel);
        emergencyContactPanel.add(phoneEmergencyContactLabel);
        emergencyContactPanel.add(relationshipLabel);
        emergencyContactPanel.add(nameEmergencyContactTextField);
        emergencyContactPanel.add(cityEmergencyContactTextField);
        emergencyContactPanel.add(ufEmergencyContactComboBox);
        emergencyContactPanel.add(phoneEmergencyContactTextField);
        emergencyContactPanel.add(relationshipTextField);

        //fingerprintPanel 
        fingerprintPanel.add(scanButton);
        fingerprintPanel.add(fingerprintTitle);
        fingerprintPanel.add(handsPanel);

        //bordas
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        // maritalStatusTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        dateBirthTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ageTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rgTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        // nascionalityTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        // cityTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        adressTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        //districtTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        numberTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cepTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        phoneTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cellphoneTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        mailTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        observationTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        nameResponsibleTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        rgResponsibleTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        phoneResponsibleTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        nameEmergencyContactTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        //   cityEmergencyContactTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        phoneEmergencyContactTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        relationshipTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //eventos
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextPanel();
            }
        });
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousPanel();
            }
        });

        maleRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                femaleRadioButton.setSelected(false);
            }
        });
        femaleRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                maleRadioButton.setSelected(false);
            }
        });
        maleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                maleRadioButton.setSelected(true);
                femaleRadioButton.setSelected(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                maleLabel.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                maleLabel.setForeground(Color.BLACK);
            }
        });
        femaleLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                femaleRadioButton.setSelected(true);
                maleRadioButton.setSelected(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                femaleLabel.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                femaleLabel.setForeground(Color.BLACK);
            }
        });
        dateBirthTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                dateBirthTextField.setBorder(emptyBorder);
            }

            public void focusLost(FocusEvent e) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.sql.Date dateBirth;
                if (!dateBirthTextField.getText().isEmpty()) {
                    try {
                        dateBirth = new java.sql.Date(dateFormat.parse(dateBirthTextField.getText()).getTime());
                        student.setDateOfBirth(dateBirth);
                        ageTextField.setText("" + student.getAge());
                        dateBirthTextField.setBorder(greenBorder);
                    } catch (ParseException ex) {
                        dateBirthTextField.setBorder(redBorder);
                    }
                } else {
                    dateBirthTextField.setBorder(redBorder);
                }
            }
        });

        //define cor
        ageTextField.setBackground(Color.LIGHT_GRAY);
        maleRadioButton.setBackground(Color.WHITE);
        femaleRadioButton.setBackground(Color.WHITE);
        personalDataPanel.setBackground(Color.WHITE);
        adressPanel.setBackground(Color.WHITE);
        contactPanel.setBackground(Color.WHITE);
        observationPanel.setBackground(Color.WHITE);
        responsiblePanel.setBackground(Color.WHITE);
        emergencyContactPanel.setBackground(Color.WHITE);
        fingerprintPanel.setBackground(Color.WHITE);
        frame.getContentPane().setBackground(Color.WHITE);
        scrollPane.setBackground(Color.WHITE);
        ufComboBox.setBackground(Color.WHITE);
        ufEmergencyContactComboBox.setBackground(Color.WHITE);
        objectiveComboBox.setBackground(Color.WHITE);

        ageTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        activeValidation();

        JContextPopupMenu contextPopupMenu = new JContextPopupMenu();

        //adiciona evento aos itens do popupmenu
        contextPopupMenu.addInComponet(nameTextField);
        contextPopupMenu.addInComponet(maritalStatusTextField);
        contextPopupMenu.addInComponet(dateBirthTextField);
        contextPopupMenu.addInComponet(ageTextField);
        contextPopupMenu.addInComponet(rgTextField);
        contextPopupMenu.addInComponet(nascionalityTextField);
        contextPopupMenu.addInComponet(cityTextField);
        contextPopupMenu.addInComponet(adressTextField);
        contextPopupMenu.addInComponet(districtTextField);
        contextPopupMenu.addInComponet(numberTextField);
        contextPopupMenu.addInComponet(cepTextField);
        contextPopupMenu.addInComponet(phoneTextField);
        contextPopupMenu.addInComponet(cellphoneTextField);
        contextPopupMenu.addInComponet(mailTextField);
        contextPopupMenu.addInComponet(nameResponsibleTextField);
        contextPopupMenu.addInComponet(rgResponsibleTextField);
        contextPopupMenu.addInComponet(phoneResponsibleTextField);
        contextPopupMenu.addInComponet(nameEmergencyContactTextField);
        contextPopupMenu.addInComponet(cityEmergencyContactTextField);
        contextPopupMenu.addInComponet(phoneEmergencyContactTextField);
        contextPopupMenu.addInComponet(relationshipTextField);

        DocumentFilter dateDocumentFilter = new DateDocumentFilter();
        ((AbstractDocument) dateBirthTextField.getDocument()).setDocumentFilter(dateDocumentFilter);

        //configurações finais
        countPanel = 1;
        scrollPane.setViewportView(personalDataPanel);
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void nextPanel() {
        boolean valid = true;
        if (countPanel == 1) {
            valid = validatePersonalDataPanel();

        } else if (countPanel == 2) {
            valid = validateAdressPanel();
        } else if (countPanel == 5) {
            age = Integer.valueOf(ageTextField.getText());
            if (age <= 18) {
                valid = validateResponsiblePanel();
            }
        } else if (countPanel == 6) {
            valid = validateEmergencyContactPanel();
        }

        if (valid) {
            countPanel++;
            if (countPanel == 2) {
                frame.add(previousButton);
                scrollPane.setViewportView(adressPanel);

            } else if (countPanel == 3) {
                scrollPane.setViewportView(contactPanel);
            } else if (countPanel == 4) {
                scrollPane.setViewportView(observationPanel);
            } else if (countPanel == 5) {
                age = Integer.valueOf(ageTextField.getText());
                if (age <= 18) {
                    scrollPane.setViewportView(responsiblePanel);
                } else {
                    nextPanel();
                }
            } else if (countPanel == 6) {
                scrollPane.setViewportView(emergencyContactPanel);
            } else if (countPanel == 7) {
                frame.remove(nextButton);
                frame.add(okButton);
                scrollPane.setViewportView(fingerprintPanel);
            }
            frame.repaint();
        }
    }

    public void previousPanel() {
        //  scrollPane.removeAll();
        countPanel--;
        if (countPanel == 1) {

            frame.remove(previousButton);
            scrollPane.setViewportView(personalDataPanel);

        } else if (countPanel == 2) {
            scrollPane.setViewportView(adressPanel);

        } else if (countPanel == 3) {
            scrollPane.setViewportView(contactPanel);
        } else if (countPanel == 4) {
            scrollPane.setViewportView(observationPanel);
        } else if (countPanel == 5) {
            if (age <= 18) {
                scrollPane.setViewportView(responsiblePanel);
            } else {
                previousPanel();
            }
        } else if (countPanel == 6) {
            frame.remove(okButton);
            frame.add(nextButton);
            scrollPane.setViewportView(emergencyContactPanel);
        }
        frame.repaint();
    }

    public void takeScreenSize() {
        ScreenResolution screenResolution = ScreenResolution.getInstance();
        x = screenResolution.getX();
        y = screenResolution.getY();
    }//fim do método takeScreenSize

    public void fillsView() {
        if (student != null) {
            nameTextField.setText(student.getName());
            maritalStatusTextField.setText(student.getMaritalStatus());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateFormated = dateFormat.format(student.getDateOfBirth());
            dateBirthTextField.setText(dateFormated);
            String gender = student.getSex();;
            if (gender.contains("M")) {

                maleRadioButton.setSelected(true);
            }
            if (gender.contains("F")) {
                femaleRadioButton.setSelected(true);
            }

            ageTextField.setText("" + student.getAge());
            rgTextField.setText("" + student.getRG());
            nascionalityTextField.setText(student.getNationality());
            cityTextField.setText(student.getCity());
            adressTextField.setText(student.getAddress());
            districtTextField.setText(student.getDistrict());
            numberTextField.setText("" + student.getNumber());
            cepTextField.setText(student.getCEP());
            phoneTextField.setText(student.getPhone());
            cellphoneTextField.setText(student.getCellPhone());
            mailTextField.setText(student.getMail());
            observationTextField.setText(student.getObservation());

            ufComboBox.setSelectedIndex(ufComparator(student.getUF()));

            if (student.getObjective() == null || student.getObjective().equals("") || student.getObjective().equals(" ")) {
                objectiveComboBox.setSelectedIndex(0);
            } else if (student.getObjective().equals("Condicionamento Físico")) {
                objectiveComboBox.setSelectedIndex(1);
            } else if (student.getObjective().equals("Prep. Especial p/ Esportes")) {
                objectiveComboBox.setSelectedIndex(2);
            } else if (student.getObjective().equals("Convívio Social")) {
                objectiveComboBox.setSelectedIndex(3);
            } else if (student.getObjective().equals("Combate ao Stress")) {
                objectiveComboBox.setSelectedIndex(4);
            } else if (student.getObjective().equals("Saúde")) {
                objectiveComboBox.setSelectedIndex(5);
            } else if (student.getObjective().equals("Terapêutica")) {
                objectiveComboBox.setSelectedIndex(6);
            } else if (student.getObjective().equals("Lazer")) {
                objectiveComboBox.setSelectedIndex(7);
            } else if (student.getObjective().equals("Estética")) {
                objectiveComboBox.setSelectedIndex(8);
            }
        }
        if (responsible != null) {
            nameResponsibleTextField.setText(responsible.getName());
            rgResponsibleTextField.setText("" + responsible.getRG());
            phoneResponsibleTextField.setText(responsible.getPhone());
        }
        if (emergencyContact != null) {
            nameEmergencyContactTextField.setText(emergencyContact.getName());
            cityEmergencyContactTextField.setText(emergencyContact.getCity());
            ufEmergencyContactComboBox.setSelectedIndex(ufComparator(emergencyContact.getUF()));
            phoneEmergencyContactTextField.setText(emergencyContact.getPhone());
            relationshipTextField.setText(emergencyContact.getRelationship());
        }

    }

    public byte getFingerSelected() {

        return handsPanel.getFingerSelected();
    }

    public void setScannedFinger(byte finger) {
        handsPanel.setScannedFinger(finger);
    }

    private int ufComparator(String uf) {
        if (uf.equals("RS")) {
            return 22;
        }
        if (uf.equals("AC")) {
            return 0;
        }
        if (uf.equals("AL")) {
            return 1;
        }
        if (uf.equals("AM")) {
            return 2;
        }
        if (uf.equals("AP")) {
            return 3;
        }
        if (uf.equals("BA")) {
            return 4;
        }
        if (uf.equals("CE")) {
            return 5;
        }
        if (uf.equals("DF")) {
            return 6;
        }
        if (uf.equals("ES")) {
            return 7;
        }
        if (uf.equals("GO")) {
            return 8;
        }
        if (uf.equals("MA")) {
            return 9;
        }
        if (uf.equals("MG")) {
            return 10;
        }
        if (uf.equals("MS")) {
            return 11;
        }
        if (uf.equals("MT")) {
            return 12;
        }
        if (uf.equals("PA")) {
            return 13;
        }
        if (uf.equals("PB")) {
            return 14;
        }
        if (uf.equals("PE")) {
            return 15;
        }
        if (uf.equals("PI")) {
            return 16;
        }
        if (uf.equals("PR")) {
            return 17;
        }
        if (uf.equals("RJ")) {
            return 18;
        }
        if (uf.equals("RN")) {
            return 19;
        }
        if (uf.equals("RO")) {
            return 20;
        }
        if (uf.equals("RR")) {
            return 21;
        }
        if (uf.equals("SC")) {
            return 23;
        }
        if (uf.equals("SE")) {
            return 24;
        }
        if (uf.equals("SP")) {
            return 25;
        }
        if (uf.equals("TO")) {
            return 26;
        }

        return -1;
    }

    public void activeValidation() {
        FocusListener testEmptyField = new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField tempField = (JTextField) e.getComponent();
                tempField.setBorder(emptyBorder);

            }

            public void focusLost(FocusEvent e) {
                testComponentyEmpty(e.getComponent());
            }
        };
        nameTextField.addFocusListener(testEmptyField);
        maritalStatusTextField.addFocusListener(testEmptyField);
        rgTextField.addFocusListener(testEmptyField);
        nascionalityTextField.addFocusListener(testEmptyField);
        cityTextField.addFocusListener(testEmptyField);
        adressTextField.addFocusListener(testEmptyField);
        districtTextField.addFocusListener(testEmptyField);
        numberTextField.addFocusListener(testEmptyField);
        cepTextField.addFocusListener(testEmptyField);
        nameResponsibleTextField.addFocusListener(testEmptyField);
        rgResponsibleTextField.addFocusListener(testEmptyField);
        phoneResponsibleTextField.addFocusListener(testEmptyField);
        nameEmergencyContactTextField.addFocusListener(testEmptyField);
        cityEmergencyContactTextField.addFocusListener(testEmptyField);
        phoneEmergencyContactTextField.addFocusListener(testEmptyField);
        relationshipTextField.addFocusListener(testEmptyField);

        rgTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField tempField = (JTextField) e.getComponent();
                tempField.setBorder(emptyBorder);
            }

            public void focusLost(FocusEvent e) {
                JTextField tempField = (JTextField) e.getComponent();
                if (tempField.getText().length() != 10) {
                    tempField.setBorder(redBorder);
                } else {
                    tempField.setBorder(greenBorder);
                }
            }
        });
        cepTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                JTextField tempField = (JTextField) e.getComponent();
                tempField.setBorder(emptyBorder);
            }

            public void focusLost(FocusEvent e) {
                JTextField tempField = (JTextField) e.getComponent();
                if (tempField.getText().length() != 8) {
                    tempField.setBorder(redBorder);
                } else {
                    tempField.setBorder(greenBorder);
                }
            }
        });

    }

    private boolean testComponentyEmpty(Component component) {
        if (component instanceof JTextField) {
            JTextField tempField = (JTextField) component;

            if (tempField.getText().isEmpty()) {
                tempField.setBorder(redBorder);
                return true;
            } else {
                tempField.setBorder(greenBorder);
                return false;
            }
        } else if (component instanceof JComboBox) {
            JAutoSuggestComboBox tempField = (JAutoSuggestComboBox) component;

            if (tempField.getText().isEmpty()) {
                tempField.setBorder(redBorder);
                return true;
            } else {
                tempField.setBorder(greenBorder);
                return false;
            }
        }
        return false;
    }

    public boolean validatePersonalDataPanel() {

        if (nameTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(nameTextField)) {
            return false;
        }
        if (maritalStatusTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(maritalStatusTextField)) {
            return false;
        }
        if (dateBirthTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(dateBirthTextField)) {
            return false;
        }

        if (rgTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(rgTextField)) {
            return false;
        }
        if (nascionalityTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(nascionalityTextField)) {
            return false;
        }

        if (!maleRadioButton.isSelected()) {
            if (!femaleRadioButton.isSelected()) {
                JOptionPane.showMessageDialog(null, "Selecione o sexo!", "Atanção!", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        return true;

    }

    public boolean validateAdressPanel() {

        if (cityTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(cityTextField)) {
            return false;
        }
        if (adressTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(adressTextField)) {
            return false;
        }
        if (districtTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(districtTextField)) {
            return false;
        }
        if (numberTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(numberTextField)) {
            return false;
        }
        if (cepTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(cepTextField)) {
            return false;
        }
        return true;
    }

    public boolean validateResponsiblePanel() {

        if (nameResponsibleTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(nameResponsibleTextField)) {
            return false;
        }
        if (rgResponsibleTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(rgResponsibleTextField)) {
            return false;
        }
        if (phoneResponsibleTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(phoneResponsibleTextField)) {
            return false;
        }
        return true;
    }

    public boolean validateEmergencyContactPanel() {

        if (nameEmergencyContactTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(nameEmergencyContactTextField)) {
            return false;
        }
        if (cityEmergencyContactTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(cityEmergencyContactTextField)) {
            return false;
        }
        if (phoneEmergencyContactTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(phoneEmergencyContactTextField)) {
            return false;
        }
        if (relationshipTextField.getBorder() == redBorder) {
            return false;
        } else if (testComponentyEmpty(relationshipTextField)) {
            return false;
        }
        return true;
    }

    public void setOKButtonActionListener(ActionListener actionListener) {
        okButton.addActionListener(actionListener);
    }

    public void setScanButtonActionListener(ActionListener actionListener) {
        scanButton.addActionListener(actionListener);
    }

    public void setCloseWindowListener(WindowListener windowListener) {
        frame.addWindowListener(windowListener);
    }

    public void close() {
        frame.dispose();
    }

    public Student getStudent() {
        if (student == null) {
            student = new Student();
        }
        try {
            student.setName(nameTextField.getText());
            student.setMaritalStatus(maritalStatusTextField.getText());
            if (maleRadioButton.isSelected()) {
                student.setSex("M");
            }
            if (femaleRadioButton.isSelected()) {
                student.setSex("F");
            }

            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            java.sql.Date dateBirth = null;

            student.setRG(rgTextField.getText());
            student.setNationality(nascionalityTextField.getText());
            student.setCity(cityTextField.getText());
            student.setAddress(adressTextField.getText());
            student.setDistrict(districtTextField.getText());
            student.setNumber(Integer.valueOf(numberTextField.getText()));
            student.setCEP(cepTextField.getText());
            student.setPhone(phoneTextField.getText());
            student.setCellPhone(cellphoneTextField.getText());
            student.setMail(mailTextField.getText());
            student.setObservation(observationTextField.getText());
            student.setUF(ufComboBox.getSelectedItem().toString());
            student.setObjective(objectiveComboBox.getSelectedItem().toString());

        } catch (IllegalArgumentException illegalArgumentException) {
            student = null;
            System.err.println("Erro: estudante com formato incorreto!");
            illegalArgumentException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: formatoo incorreto!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(illegalArgumentException);
        }
        return student;
    }

    public EmergencyContact getEmergencyContact() {
        if (emergencyContact == null) {
            emergencyContact = new EmergencyContact();
        }
        try {
            emergencyContact.setCity(cityEmergencyContactTextField.getText());
            emergencyContact.setName(nameEmergencyContactTextField.getText());
            emergencyContact.setPhone(phoneEmergencyContactTextField.getText());
            emergencyContact.setRelationship(relationshipTextField.getText());
            emergencyContact.setUF(ufEmergencyContactComboBox.getSelectedItem().toString());
        } catch (IllegalArgumentException illegalArgumentException) {
            emergencyContact = null;
            System.err.println("Erro: contato de emergência com formato incorreto!");
            illegalArgumentException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: formatoo incorreto!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(illegalArgumentException);
        }
        return emergencyContact;
    }

    public Responsible getResponsible() {
        if (responsible == null) {
            responsible = new Responsible();
        }
        try {
            responsible.setName(nameResponsibleTextField.getText());
            responsible.setPhone(phoneResponsibleTextField.getText());
            responsible.setRG(rgResponsibleTextField.getText());
        } catch (IllegalArgumentException illegalArgumentException) {
            responsible = null;
            System.err.println("Erro: responsável com formato incorreto!");
            illegalArgumentException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro: formatoo incorreto!", "Erro!", JOptionPane.ERROR_MESSAGE);
            LogMaker.create(illegalArgumentException);
        }
        return responsible;
    }

    public void setNascionalityList(List<String> list) {
        nascionalityTextField.setSuggestList(list);
    }

    public void setMaritalStatusList(List<String> list) {
        maritalStatusTextField.setSuggestList(list);
    }

    public void setCityList(List<String> list) {
        cityTextField.setSuggestList(list);
    }

    public void setDistrictList(List<String> list) {
        districtTextField.setSuggestList(list);
    }

    public void setCityEmergencyContactTList(List<String> list) {
        cityEmergencyContactTextField.setSuggestList(list);
    }

    public static void main(String args[]) {
        AddStudentFrame addStudentFrame = new AddStudentFrame(new Student("name", "Masculino", "maritalStatus",
                "nationality", "city", "RS", "address", "district", 0, "CEP", "phone",
                "cellPhone", "mail", new Date(1000000), "0", "objective",
                "observation"), new EmergencyContact(0,
                        "name",
                        "phone",
                        "city",
                        "RS",
                        "relationship"));
    }
}//fim da classe AddStudentFrame

