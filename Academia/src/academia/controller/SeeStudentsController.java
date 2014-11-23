
/*-
 * Classname:             SeeStudentsController.java
 *
 * Version information:   (versão)
 *
 * Date:                  25/02/2013 - 20:21:10
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.StudentDAO;
import academia.model.Point;
import academia.model.Student;
import academia.util.ObserverInterface;
import academia.view.SeeStudentPanel;
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
public class SeeStudentsController implements ObserverInterface {

    private SeeStudentPanel seeStudentPanel;
    private StudentDAO studentDAO;
    private ObserverInterface observerInterface;
    private List<Student> students;
    private List<Point> points;
    private String title;

    public SeeStudentsController() {
        title = "Alunos";
        students = new ArrayList();
        points = new ArrayList();

        studentDAO = new StudentDAO();
        Iterator studentsIterator = studentDAO.searchStudent();

        while (studentsIterator.hasNext()) {
            Student student = (Student) studentsIterator.next();
            students.add(student);

        }
        seeStudentPanel = new SeeStudentPanel(students);
        seeStudentPanel.setActionListenerSeeButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seeStudent();
            }
        });
        seeStudentPanel.setActionListenerAddButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        seeStudentPanel.setActionListenerHistoryButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seeHistory();
            }
        });
        seeStudentPanel.setActionListenerEditButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });
        seeStudentPanel.setActionListenerEraseButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eraseStudent();
            }
        });
    }

    public SeeStudentsController(Iterator iterator) {
        title = "Resultados da Busca";
        students = new ArrayList();
        while (iterator.hasNext()) {
            students.add((Student) iterator.next());
        }
        seeStudentPanel = new SeeStudentPanel(students);
        seeStudentPanel.setTitle(title);
        seeStudentPanel.setActionListenerSeeButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seeStudent();
            }
        });
        seeStudentPanel.setActionListenerAddButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        seeStudentPanel.setActionListenerHistoryButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seeHistory();
            }
        });
        seeStudentPanel.setActionListenerEditButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });
        seeStudentPanel.setActionListenerEraseButton(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eraseStudent();
            }
        });
    }

    public void seeStudent() {
        Student student = seeStudentPanel.getRowSelected();
        if (student != null) {
            VisualizeController visualizeController = new VisualizeController(student);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um aluno!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void addStudent() {
        AddStudentController addStudentController = new AddStudentController();
        addStudentController.registerObserver(this);
        addStudentController.registerObserver(observerInterface);
        addStudentController.notifyObservers();
    }

    public void seeHistory() {
        Student student = seeStudentPanel.getRowSelected();
        if (student != null) {
            HistoryController historyController = new HistoryController(student);
            historyController.registerObserver(this);
            historyController.registerObserver(observerInterface);
            historyController.notifyObservers();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um aluno!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void editStudent() {
        Student student = seeStudentPanel.getRowSelected();
        if (student != null) {
            AddStudentController editStudentController = new AddStudentController(student);
            editStudentController.registerObserver(this);
            editStudentController.registerObserver(observerInterface);
            editStudentController.notifyObservers();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um aluno!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void eraseStudent() {
        Student student = seeStudentPanel.getRowSelected();
        if (student != null) {
            int option = JOptionPane.showConfirmDialog(null, "Deseja excluir " + student.getName(), "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == 0) {
                studentDAO.eraseStudent(student);
                seeStudentPanel.remove(seeStudentPanel.getRowIndexSelected());
                ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
                threadDeviceController.refresh();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um aluno!", "Atenção!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void registerInAllObservers(ObserverInterface observerInterface) {
        this.observerInterface = observerInterface;
    }

    public void update(boolean active, Object object) {
        if (active) {
            if (object instanceof Student) {
                students.clear();
                Iterator studentsIterator = studentDAO.searchStudent();
                while (studentsIterator.hasNext()) {
                    Student student = (Student) studentsIterator.next();
                    students.add(student);
                }
                seeStudentPanel.refresh(students);

            }
        }
    }

    public JPanel getPanel() {
        return seeStudentPanel.getPanel();
    }
}//fim da classe SeeStudentsController

