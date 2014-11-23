
/*-
 * Classname:             AddPointController.java
 *
 * Version information:   (versão)
 *
 * Date:                  28/02/2013 - 18:11:28
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
import academia.view.AddPointFrame;
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
public class AddPointController implements SubjectInterface {

    private AddPointFrame addPointFrame;
    private Point point;
    private Student student;
    private boolean active = false;
    private List<ObserverInterface> observers;

    public AddPointController(Student student) {
        observers = new ArrayList();
        addPointFrame = new AddPointFrame();
        this.student = student;

        addPointFrame.setActionListenerOkButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPoint();
            }
        });
        addPointFrame.setActionListenerCancelButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
    }

    public AddPointController(Student student, Point point) {
        observers = new ArrayList();
        addPointFrame = new AddPointFrame(point);
        this.student = student;
        this.point = point;

        addPointFrame.setActionListenerOkButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPoint();
            }
        });
        addPointFrame.setActionListenerCancelButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        addPointFrame.setCloseWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Os dados não salvos serão perdidos!  Deseja continuar?", "Atenção!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    cancel();
                }
            }
        });
    }

    public AddPointController(Point point) {
        observers = new ArrayList();
        addPointFrame = new AddPointFrame(point);
        this.point = point;

        addPointFrame.setActionListenerOkButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPoint();
            }
        });
        addPointFrame.setActionListenerCancelButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        addPointFrame.setCloseWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Os dados não salvos serão perdidos!  Deseja continuar?", "Atenção!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (option == 0) {
                    cancel();
                }
            }
        });
    }

    public void addPoint() {
        PointDAO pointDAO = new PointDAO();
        point = addPointFrame.getPoint();

        if (point != null) {
            point.setCodeStudent(student.getCodeStudent());
            point.setCodePoint(pointDAO.addPoint(point));
            active = true;
            addPointFrame.close();
            notifyObservers();
        }

    }

    public void editPoint() {
        PointDAO pointDAO = new PointDAO();
        int codePoint = point.getCodePoint();
        int codeStudent = point.getCodeStudent();
        Point lastPount = point;
        point = addPointFrame.getPoint();

        if (point != null) {
            point.setCodePoint(codePoint);
            point.setCodeStudent(codeStudent);
            pointDAO.editPoint(point);
            active = true;
            addPointFrame.close();
            notifyObservers();
        }else{
            point=lastPount;
        }
    }

    public void cancel() {
        addPointFrame.close();
        point = null;
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
            observer.update(active, point);
        }
    }
}//fim da classe AddPointController

