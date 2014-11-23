/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.controller;

import academia.DAO.PointDAO;
import academia.DAO.StudentDAO;
import academia.model.Point;
import academia.model.Student;
import academia.util.Observer;
import academia.util.ObserverInterface;
import academia.util.SubjectInterface;
import academia.view.PointFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonas
 */
public class PointController implements SubjectInterface, ObserverInterface, Observer {
    
    private PointFrame view;
    private List<ObserverInterface> observers;
    private boolean active = false;
    
    public PointController() {
        observers = new ArrayList();
        PointDAO pointDAO = new PointDAO();
        StudentDAO studentDAO = new StudentDAO();
        
        Iterator iterator = pointDAO.searchPoints();
        List<Point> points = new ArrayList();
        List<Student> students = new ArrayList();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            points.add(point);
            students.add(studentDAO.searchStudentForCode(point.getCodeStudent()));
        }
        view = new PointFrame(students, points);
        
        view.setActionListenerEditButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPoint();
            }
        });
        view.setActionListenerEraseButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                erasePoint();
            }
        });
        view.setCloseWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                cancel();
            }
        });
        
        ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
        threadDeviceController.registerObserver(this);
    }
    
    public void editPoint() {
        Point point = view.getRow();
        if (point != null) {
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.searchStudentForCode(point.getCodeStudent());
            AddPointController addPointContrroler = new AddPointController(student, point);
            addPointContrroler.registerObserver(this);
            addPointContrroler.notifyObservers();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ponto!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void erasePoint() {
        Point point = view.getRow();
        if (point != null) {
            int option = JOptionPane.showConfirmDialog(null, "Deseja excluir o ponto selecionado?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == 0) {
                PointDAO pointDAO = new PointDAO();
                pointDAO.erasePoint(point);
                view.remove(view.getRowIndex());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um ponto!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    public void cancel() {
        view.close();
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
        
        view.setEnable(active);
        view.requestFrameFocus();
        
        if (active) {
            if (object instanceof Point) {
                PointDAO pointDAO = new PointDAO();
                StudentDAO studentDAO = new StudentDAO();
                
                Iterator iterator = pointDAO.searchPoints();
                List<Point> points = new ArrayList();
                List<Student> students = new ArrayList();
                while (iterator.hasNext()) {
                    Point point = (Point) iterator.next();
                    points.add(point);
                    students.add(studentDAO.searchStudentForCode(point.getCodeStudent()));
                }
                view.refresh(students, points);
                
            }
        }
    }

    public void update(Object object) {
        PointDAO pointDAO = new PointDAO();
        StudentDAO studentDAO = new StudentDAO();
        
        Iterator iterator = pointDAO.searchPoints();
        List<Point> points = new ArrayList();
        List<Student> students = new ArrayList();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            points.add(point);
            students.add(studentDAO.searchStudentForCode(point.getCodeStudent()));
        }
        view.refresh(students, points);
    }
}
