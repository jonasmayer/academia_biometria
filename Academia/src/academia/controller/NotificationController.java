
/*-
 * Classname:             NotificationController.java
 *
 * Version information:   (versão)
 *
 * Date:                  05/03/2013 - 17:48:38
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.PointDAO;
import academia.DAO.StudentDAO;
import academia.model.Point;
import academia.model.Student;
import academia.util.ObserverInterface;
import academia.view.NotificationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class NotificationController implements ObserverInterface {

    private final int OPEN_POINT = 1;
    private NotificationPanel notificationPanel;
    private Iterator iterator;
    private List<Student> students;
    private List<Point> points;
    private List<String> notes;
    private ObserverInterface observerInterface;

    public NotificationController() {
        List type = new ArrayList();
        StudentDAO studentDAO = new StudentDAO();
        PointDAO pointDAO = new PointDAO();
        iterator = pointDAO.searchOpenPoint();
        students = new ArrayList();
        points = new ArrayList();
        notes = new ArrayList();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            points.add(point);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            notes.add("Ponto do dia " + format.format(point.getDay()) + " e hora " + point.getEntry() + " não foi fechado!");
            students.add(studentDAO.searchStudentForCode(point.getCodeStudent()));
            type.add(OPEN_POINT);
        }

        notificationPanel = new NotificationPanel(students, notes, type);
        notificationPanel.setEditButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPoint();

            }
        });
        notificationPanel.setEraseButtonActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                erasePoint();

            }
        });

    }

    public void editPoint() {
        int row = notificationPanel.getRow();
        if (row >= 0) {
            AddPointController addPointController = new AddPointController(points.get(row));
            addPointController.registerObserver(this);
            addPointController.registerObserver(observerInterface);
            addPointController.notifyObservers();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma nota!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void erasePoint() {
        if (notificationPanel.getRow() >= 0) {
            int option = JOptionPane.showConfirmDialog(null, "Deseja excluir registro?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == 0) {

                int row = notificationPanel.getRow();
                if (row >= 0) {
                    PointDAO pointDAO = new PointDAO();
                    pointDAO.erasePoint(points.get(row));
                    points.remove(row);
                    notificationPanel.remove(row);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma nota!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void registerAllObservers(ObserverInterface observerInterface) {
        this.observerInterface = observerInterface;
    }

    public void update(boolean active, Object object) {

        if (active) {
            Point point = (Point) object;
            if (point != null) {
                for (int i = 0; i < points.size(); i++) {
                    if (point.getCodePoint() == points.get(i).getCodePoint()) {
                        points.remove(i);
                        notificationPanel.remove(i);
                    }
                }
            }
        }
    }

    public JPanel getPanel() {
        return notificationPanel.getPanel();
    }
}//fim da classe NotificationController

