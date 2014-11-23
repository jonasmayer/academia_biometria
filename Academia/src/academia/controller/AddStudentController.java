
/*-
 * Classname:             AddStudentController.java
 *
 * Version information:   (versão)
 *
 * Date:                  25/02/2013 - 18:11:27
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.EmergencyContactDAO;
import academia.DAO.FingerprintDAO;
import academia.DAO.ResponsibleDAO;
import academia.DAO.StudentDAO;
import academia.model.EmergencyContact;
import academia.model.Fingerprint;
import academia.model.Responsible;
import academia.model.Student;
import academia.util.ObserverInterface;
import academia.util.SubjectInterface;
import academia.view.AddStudentFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class AddStudentController implements SubjectInterface {

    private AddStudentFrame addStudentFrame;
    private Student student;
    private EmergencyContact emergencyContact;
    private Responsible responsible;
    private List<ObserverInterface> observers;
    private boolean active = false;
    private List<Fingerprint> fingerprints;

    public AddStudentController() {
        fingerprints = new ArrayList();
        observers = new ArrayList();
        addStudentFrame = new AddStudentFrame();
        StudentDAO studentDAO = new StudentDAO();
        addStudentFrame.setNascionalityList(studentDAO.searchNascionalityList());
        addStudentFrame.setMaritalStatusList(studentDAO.searchMaritalStatusList());
        addStudentFrame.setCityList(studentDAO.searchCityList());
        addStudentFrame.setDistrictList(studentDAO.searchDistrictLisList());
        addStudentFrame.setCityEmergencyContactTList(studentDAO.searchCityList());
        addStudentFrame.setScanButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scanFinger();
            }
        });
        addStudentFrame.setCloseWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Os dados não salvos serão perdidos!  Deseja continuar?", "Atenção!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    addStudentFrame.close();
                    student = null;
                    active = true;
                    notifyObservers();
                }
            }
        });
        addStudentFrame.setOKButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finishCadastre();
            }
        });

    }

    public AddStudentController(Student studentEdit) {

        observers = new ArrayList();
        student = studentEdit;
        EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
        emergencyContact = emergencyContactDAO.searchEmergencyContact(student);
        if (student.getAge() < 18) {
            ResponsibleDAO responsibleDAO = new ResponsibleDAO();
            responsible = responsibleDAO.searchResponsible(student);
            addStudentFrame = new AddStudentFrame(studentEdit, emergencyContact, responsible);
        } else {
            addStudentFrame = new AddStudentFrame(studentEdit, emergencyContact);
        }
        addStudentFrame.setCloseWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Os dados não salvos serão perdidos!  Deseja continuar?", "Atenção!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    addStudentFrame.close();
                    student = null;
                    active = true;
                    notifyObservers();
                }
            }
        });
        addStudentFrame.setScanButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scanFinger();
            }
        });
        addStudentFrame.setOKButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editRegistry();
            }
        });

        FingerprintDAO fingerpeintDAo = new FingerprintDAO();
        fingerprints = fingerpeintDAo.listFingerprintStudent(student.getCodeStudent());
        if (fingerprints == null) {
            fingerprints = new ArrayList();
        }
        for (int i = 0; i < fingerprints.size(); i++) {
            try {
                byte finger = Byte.valueOf(fingerprints.get(i).getFinger());
                addStudentFrame.setScannedFinger(finger);
            } catch (Exception ex) {
                ex.printStackTrace();
                byte b = 0;
                addStudentFrame.setScannedFinger(b);
            }
        }
    }

    public void finishCadastre() {

        //- verificar de a digital foi cadastrada ou se a digital desse cliente
        StudentDAO studentDAO = new StudentDAO();
        student = addStudentFrame.getStudent();
        if (student != null) {
            student.setCodeStudent(studentDAO.registerStudent(student));
            EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
            emergencyContact = addStudentFrame.getEmergencyContact();

            if (emergencyContact != null) {
                emergencyContact.setCodeStudent(student.getCodeStudent());
                emergencyContactDAO.registerEmergencyContact(emergencyContact);
            }
            if (student.getAge() < 18) {
                ResponsibleDAO responsibleDAO = new ResponsibleDAO();
                responsible = addStudentFrame.getResponsible();
                if (responsible != null) {
                    responsible.setCodeStudent(student.getCodeStudent());
                    responsibleDAO.registerResponsible(responsible);
                }
            }
            for (int i = 0; i < fingerprints.size(); i++) {
                Fingerprint fingerprint = fingerprints.get(i);
                fingerprint.setCodeStudent(student.getCodeStudent());
                FingerprintDAO fingerprintDAO = new FingerprintDAO();
                fingerprintDAO.addFingerprint(fingerprint);
            }

            addStudentFrame.close();
            active = true;
            notifyObservers();
            ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
            threadDeviceController.refresh();
        }
    }

    private void scanFinger() {

        //        /*
        RegisterFingerprint registerfingerprint = new RegisterFingerprint();
        Fingerprint fingerprint = registerfingerprint.scan();
        fingerprint.setFinger("0" + addStudentFrame.getFingerSelected());
        for (int i = 0; i < fingerprints.size(); i++) {
            byte finger = Byte.valueOf(fingerprints.get(i).getFinger());
            if (finger == addStudentFrame.getFingerSelected()) {
                fingerprints.remove(i);
            }
        }
        fingerprints.add(fingerprint);
        addStudentFrame.setScannedFinger(addStudentFrame.getFingerSelected());

        // */
    }

    public void editRegistry() {
        
         StudentDAO studentDAO = new StudentDAO();
         int codeStudent = student.getCodeStudent();
         student = addStudentFrame.getStudent();
         if (student != null) {
         student.setCodeStudent(codeStudent);
         studentDAO.editStudent(student);
         EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
         int codeContact = emergencyContact.getCodeContact();
         emergencyContact = addStudentFrame.getEmergencyContact();

         if (emergencyContact != null) {
         emergencyContact.setCodeContact(codeContact);
         emergencyContactDAO.editEmergencyContact(emergencyContact);
         }
         if (student.getAge() < 18) {
         ResponsibleDAO responsibleDAO = new ResponsibleDAO();
         int codeResponsible = responsible.getCodeResponsible();
         responsible = addStudentFrame.getResponsible();
         responsible.setCodeResponsible(codeResponsible);
         if (responsible != null) {
         responsible.setCodeStudent(codeStudent);
         responsibleDAO.editResponsible(responsible);
         }
         }
         for (int i = 0; i < fingerprints.size(); i++) {
         Fingerprint fingerprint = fingerprints.get(i);
         fingerprint.setCodeStudent(student.getCodeStudent());
         FingerprintDAO fingerprintDAO = new FingerprintDAO();
         fingerprintDAO.addFingerprint(fingerprint);
         }
        addStudentFrame.close();
        active = true;
        notifyObservers();
        ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
        threadDeviceController.refresh();
          }
    }

    public void registerObserver(ObserverInterface o) {
        observers.add(o);
    }

    public void removeObserver(ObserverInterface o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            ObserverInterface observer = observers.get(i);
            observer.update(active, student);
        }
    }
}//fim da classe AddStudentController

