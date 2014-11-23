
/*-
 * Classname:             BiometricReaderController.java
 *
 * Version information:   (versão)
 *
 * Date:                  22/02/2013 - 17:46:08
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.controller;

import academia.DAO.FingerprintDAO;
import academia.DAO.PointDAO;
import academia.DAO.StudentDAO;
import academia.model.Fingerprint;
import academia.model.Point;
import academia.model.Student;
import academia.util.Observer;
import academia.util.PlaySound;
import academia.util.Subject;
import academia.view.ControlPainel;
import com.nitgen.SDK.BSP.NBioBSPJNI;
import com.nitgen.SDK.BSP.NBioBSPJNI.INPUT_FIR;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class BiometricReaderController implements Runnable, Subject {

    private List<Observer> observers;
    private Device device;
    private NBioBSPJNI bsp;
    private ControlPainel view;
    private FingerprintDAO fingerprintDAO;
    private List<Fingerprint> fingerprints;
    private PointDAO pointDAO;
    private StudentDAO studentDAO;

    public BiometricReaderController(ControlPainel view) {
        fingerprintDAO = new FingerprintDAO();
        pointDAO = new PointDAO();
        studentDAO = new StudentDAO();
        observers = new ArrayList();

        device = Device.getInstance();
        device.start();
        bsp = device.getBSP();
        this.view = view;

    }

    public void run() {
        //busca

        fingerprints = fingerprintDAO.listFingerprint();
        if (fingerprints.isEmpty() || fingerprints == null) {
            view.addNote("Não ha digitais cadastradas.");
        }
        while (!Thread.currentThread().isInterrupted()) {

            try {
                scanFinger();
                Thread.sleep(2000);
            } catch (InterruptedException ep) {
                Thread.currentThread().interrupt();
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
                ex.printStackTrace();
            }

        }
    }

    private void scanFinger() {
        if (bsp != null) {
            Boolean fingerPlaced = new Boolean(false);
            bsp.CheckFinger(fingerPlaced);
            System.out.println(fingerPlaced);
            if (fingerPlaced) {
                NBioBSPJNI.FIR_HANDLE hSavedFIR = bsp.new FIR_HANDLE();
                NBioBSPJNI.WINDOW_OPTION option = bsp.new WINDOW_OPTION();
                option.WindowStyle = NBioBSPJNI.WINDOW_STYLE.INVISIBLE;
                bsp.Capture(NBioBSPJNI.FIR_PURPOSE.VERIFY, hSavedFIR, -1, null, option);

                NBioBSPJNI.FIR fullSavedFIR = bsp.new FIR();
                bsp.GetFIRFromHandle(hSavedFIR, fullSavedFIR);
                NBioBSPJNI.INPUT_FIR nowFIR = bsp.new INPUT_FIR();
                nowFIR.SetFullFIR(fullSavedFIR);

                NBioBSPJNI.IndexSearch indexSearchEngine = bsp.new IndexSearch();
                NBioBSPJNI.IndexSearch.SAMPLE_INFO sampleInfo = indexSearchEngine.new SAMPLE_INFO();

                for (int i = 0; i < fingerprints.size(); i++) {
                    NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = bsp.new FIR_TEXTENCODE();
                    textSavedFIR.TextFIR = fingerprints.get(i).getFir();
                    NBioBSPJNI.INPUT_FIR inputFIR = bsp.new INPUT_FIR();
                    inputFIR.SetTextFIR(textSavedFIR);
                    indexSearchEngine.AddFIR(inputFIR, fingerprints.get(i).getCodeStudent(), sampleInfo);

                }
                //busca
                NBioBSPJNI.IndexSearch.FP_INFO fpInfo = indexSearchEngine.new FP_INFO();
                //inputFIR nova
                indexSearchEngine.Identify(nowFIR, 5, fpInfo, 0);
                int codeStudent = fpInfo.ID;
                System.out.println(codeStudent);
                if (bsp.IsErrorOccured() == false && codeStudent > 0) {
                    //registra entrada/saida e mosta no painel

                    Point point = pointDAO.searchOpenPoint(codeStudent);

                    if (point != null) {
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
                        gc.set(GregorianCalendar.MINUTE, 0);
                        gc.set(GregorianCalendar.SECOND, 0);

                        TimeZone timeZone = TimeZone.getDefault();
                        long differenceZone = timeZone.getOffset(System.currentTimeMillis());
                        differenceZone = differenceZone * -1;
                        java.sql.Time actual = new java.sql.Time(System.currentTimeMillis() - gc.getTimeInMillis() + differenceZone - 8000);
                        //java.sql.Time entry = new java.sql.Time(point.getEntry().getTime());
                        //System.out.println(entry + " --- " + actual + " =" + point.getEntry().after(actual));
                        if (point.getEntry().before(actual)) {

                            point.setExit(new java.sql.Time(System.currentTimeMillis()));
                            pointDAO.editPoint(point);

                            Student student = studentDAO.searchStudentForCode(codeStudent);
                            view.addNote(new Time(System.currentTimeMillis()), student.getName() + " saiu.");
                            new PlaySound(PlaySound.BYE).start();
                            notifyObservers();
                        } else {

                            Student student = studentDAO.searchStudentForCode(codeStudent);
                            view.addNote(new Time(System.currentTimeMillis()), student.getName() + " retire seu dedo.");

                        }
                    } else {
                        //checa se ultima entrada foi feita a instantes
                        Point lastPoint = pointDAO.searchLastPoint(codeStudent);
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
                        gc.set(GregorianCalendar.MINUTE, 0);
                        gc.set(GregorianCalendar.SECOND, 0);

                        TimeZone timeZone = TimeZone.getDefault();
                        long differenceZone = timeZone.getOffset(System.currentTimeMillis());
                        differenceZone = differenceZone * -1;
                        java.sql.Time actual = new java.sql.Time(System.currentTimeMillis() - gc.getTimeInMillis() + differenceZone - 8000);
                        if (lastPoint == null) {
                            lastPoint = new Point();
                            lastPoint.setExit(new Time(1));
                        }
                        if (lastPoint.getExit().after(actual)) {
                            Student student = studentDAO.searchStudentForCode(codeStudent);
                            view.addNote(new Time(System.currentTimeMillis()), student.getName() + " retire seu dedo.");
                        } else {
                            point = new Point();
                            point.setCodeStudent(codeStudent);

                            point.setDay(new java.sql.Date(System.currentTimeMillis()));
                            point.setEntry(new java.sql.Time(System.currentTimeMillis()));
                            pointDAO.addPoint(point);
                            Student student = studentDAO.searchStudentForCode(codeStudent);
                            view.addNote(new Time(System.currentTimeMillis()), student.getName() + " entrou.");
                            new PlaySound(PlaySound.WELCOME).start();
                            notifyObservers();
                        }
                    }

                } else {
                    view.addNote("Erro: Não foi possível identificar digital!");
                }
            }
        } else {
            view.addNote("Não foi encontrado nenhum dispositivo!");
            device = Device.getInstance();
            device.start();
            bsp = device.getBSP();
        }
    }

    /*  public synchronized void pause() {
     try {
     System.out.println("PAUSADO");
     this.wait();

     } catch (InterruptedException ex) {
     ex.printStackTrace();
     }

     }

     public synchronized void resume() {
     System.out.println("RESUME");
     // device = Device.getInstance();
     this.notify();
     }*/
    public synchronized void refresh() {
        try {

            fingerprints = fingerprintDAO.listFingerprint();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void registerObserver(Observer o) {
        observers.add(o);

    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(true);
        }
    }

    public static void main(String args[]) {
        try {
            ThreadDeviceController t = ThreadDeviceController.getInstance();
            t.start();
            while (true) {
                System.out.println("-@-");
                new RegisterFingerprint().scan();
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}//fim da classe BiometricReaderController

