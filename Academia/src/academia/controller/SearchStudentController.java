
/*-
 * Classname:             SearchStudentController.java
 *
 * Version information:   (versão)
 *
 * Date:                  05/03/2013 - 18:40:35
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.StudentDAO;
import academia.util.ObserverInterface;
import academia.util.SubjectInterface;
import academia.view.SearchStudentFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class SearchStudentController implements SubjectInterface {

    private SearchStudentFrame searchStudentFrame;
    private List<ObserverInterface> observers;
    private SeeStudentsController seeStudentsController;
    private boolean active = false;

    public SearchStudentController() {
        observers = new ArrayList();
        searchStudentFrame = new SearchStudentFrame();
        searchStudentFrame.setOKButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });
        searchStudentFrame.setCancelButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    public void searchStudent() {
        StudentDAO studentDAO = new StudentDAO();
        Iterator iterator = null;
        String term = searchStudentFrame.getSearch();
        if (term.isEmpty()) {
            return;
        }
        term = term.toLowerCase();
        if (searchStudentFrame.getSearchType() == 0) {

            iterator = studentDAO.searchStudentByName(term);

        } else if (searchStudentFrame.getSearchType() == 1) {

            iterator = studentDAO.searchStudentByRg(term);

        }
        searchStudentFrame.close();
        seeStudentsController = new SeeStudentsController(iterator);

        active = true;
        notifyObservers();

    }

    public void cancel() {
        searchStudentFrame.close();
        active = true;
        notifyObservers();
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
            observer.update(active, seeStudentsController);
        }
    }
}//fim da classe SearchStudentController

