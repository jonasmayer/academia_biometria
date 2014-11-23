
/*-
 * Classname:             VisualizeController.java
 *
 * Version information:   (versão)
 *
 * Date:                  27/02/2013 - 18:04:26
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.EmergencyContactDAO;
import academia.DAO.ResponsibleDAO;
import academia.model.EmergencyContact;
import academia.model.Responsible;
import academia.model.Student;
import academia.view.VisualizeFrame;

/**
 * Descrição
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class VisualizeController {

    public VisualizeController(Student student) {
        EmergencyContactDAO emergencyContactDAO = new EmergencyContactDAO();
        EmergencyContact emergencyContact = emergencyContactDAO.searchEmergencyContact(student);
        if (student.getAge() < 18) {
            ResponsibleDAO responsibleDAO = new ResponsibleDAO();
            Responsible responsible = responsibleDAO.searchResponsible(student);
            VisualizeFrame visualizeFrame = new VisualizeFrame(student, emergencyContact, responsible);
        } else {
            VisualizeFrame visualizeFrame = new VisualizeFrame(student, emergencyContact);
        }
    }
}//fim da classe VisualizeController
