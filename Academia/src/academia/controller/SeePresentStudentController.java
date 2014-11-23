
/*-
 * Classname:             SeePresentStudentController.java
 *
 * Version information:   (versão)
 *
 * Date:                  01/03/2013 - 17:19:18
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.PointDAO;
import academia.DAO.StudentDAO;
import academia.model.Point;
import academia.model.Student;
import academia.util.Observer;
import academia.util.ObserverInterface;
import academia.view.StudentPresentPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class SeePresentStudentController implements ObserverInterface, Observer {

    private StudentPresentPanel studentPresentPanel;
    private ObserverInterface observerInterface;

    public SeePresentStudentController() {
        List<Student> students = new ArrayList();
        List<Point> points = new ArrayList();
        PointDAO pointDAO = new PointDAO();
        StudentDAO studentDAO = new StudentDAO();
        Iterator iterator = pointDAO.searchOpenPoint();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            points.add(point);
            students.add(studentDAO.searchStudentForCode(point.getCodeStudent()));
        }

        studentPresentPanel = new StudentPresentPanel(students, points);

        studentPresentPanel.setActionListenerGenerateOutputButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPoint();
            }
        });

        ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
        threadDeviceController.registerObserver(this);
    }

    public void editPoint() {
        Student student = null;
        Point point = studentPresentPanel.getSelectedPoint();
        if (point != null) {
            int codeStudent = point.getCodeStudent();
            StudentDAO studentDAO = new StudentDAO();
            student = studentDAO.searchStudentForCode(codeStudent);

            AddPointController addPointController = new AddPointController(student, point);
            addPointController.registerObserver(this);
            addPointController.registerObserver(observerInterface);
            addPointController.notifyObservers();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um aluno!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void registerInAllObserbers(ObserverInterface observerInterface) {
        this.observerInterface = observerInterface;
    }

    public void update(boolean active, Object object) {
        if (active) {
            if (object instanceof Point) {
                List<Student> students = new ArrayList();
                List<Point> points = new ArrayList();
                PointDAO pointDAO = new PointDAO();
                StudentDAO studentDAO = new StudentDAO();
                Iterator iterator = pointDAO.searchOpenPoint();
                while (iterator.hasNext()) {
                    Point point = (Point) iterator.next();
                    points.add(point);
                    students.add(studentDAO.searchStudentForCode(point.getCodeStudent()));
                }
                studentPresentPanel.update(students, points);

            }
        }
    }

    public void update(Object object) {

        List<Student> students = new ArrayList();
        List<Point> points = new ArrayList();
        PointDAO pointDAO = new PointDAO();
        StudentDAO studentDAO = new StudentDAO();
        Iterator iterator = pointDAO.searchOpenPoint();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            points.add(point);
            students.add(studentDAO.searchStudentForCode(point.getCodeStudent()));
        }
        studentPresentPanel.update(students, points);
    }

    public JPanel getPanel() {
        return studentPresentPanel.getPanel();
    }
}//fim da classe SeePresentStudentController

