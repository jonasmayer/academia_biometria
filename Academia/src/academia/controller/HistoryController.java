
/*-
 * Classname:             HistoryController.java
 *
 * Version information:   (versão)
 *
 * Date:                  27/02/2013 - 17:42:44
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.PointDAO;
import academia.model.Point;
import academia.model.Student;
import academia.util.ObserverInterface;
import academia.util.SubjectInterface;
import academia.view.HistoryFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class HistoryController implements SubjectInterface, ObserverInterface {

    private HistoryFrame historyFrame;
    private List<ObserverInterface> observers;
    private boolean active = false;
    private Student student;

    public HistoryController(Student student) {
        observers = new ArrayList();
        this.student = student;
        PointDAO pointDAO = new PointDAO();
        Iterator points = pointDAO.searchPointForStudent(student.getCodeStudent());

        historyFrame = new HistoryFrame(student, points);
        historyFrame.setActionListenerAddButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPoint();
            }
        });
        historyFrame.setActionListenerEditButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPoint();
            }
        });
        historyFrame.setActionListenerEraseButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                erasePoint();
            }
        });
        historyFrame.setCloseWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                historyFrame.close();
                active = true;
                notifyObservers();
            }
        });

    }

    public void addPoint() {
        AddPointController addPointContrroler = new AddPointController(student);
        addPointContrroler.registerObserver(this);
        addPointContrroler.notifyObservers();


    }

    public void editPoint() {
        Point point = historyFrame.getRow();
        if (point != null) {
            AddPointController addPointContrroler = new AddPointController(student, point);
            addPointContrroler.registerObserver(this);
            addPointContrroler.notifyObservers();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ponto!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void erasePoint() {
        Point point = historyFrame.getRow();
        if (point != null) {
            int option = JOptionPane.showConfirmDialog(null, "Deseja excluir o ponto selecionado?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == 0) {
                PointDAO pointDAO = new PointDAO();
                pointDAO.erasePoint(point);
                historyFrame.remove(historyFrame.getRowIndex());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ponto!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
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
            observer.update(active, null);
        }
    }

    public void update(boolean active, Object object) {

        if (active == true) {
            this.active = true;
            notifyObservers();
            this.active = false;
            notifyObservers();
        }

        historyFrame.setEnable(active);
        historyFrame.requestFrameFocus();

        if (active) {
            if (object instanceof Point) {
                Point point = (Point) object;
                PointDAO pointDAO = new PointDAO();
                Iterator points = pointDAO.searchPointForStudent(student.getCodeStudent());
                historyFrame.refresh(points);

            }
        }
    }
}//fim da classe HistoryController

