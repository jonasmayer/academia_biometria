/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.controller;

import academia.model.Fingerprint;
import com.nitgen.SDK.BSP.NBioBSPJNI;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonas
 */
public class RegisterFingerprint {

    private NBioBSPJNI bsp;

    public RegisterFingerprint() {
        Device device = Device.getInstance();
        bsp = device.getBSP();
    }

    public Fingerprint scan() {
        ThreadDeviceController threadDeviceController = ThreadDeviceController.getInstance();
        threadDeviceController.stop();
        Fingerprint fingerprint = null;
        if (bsp != null) {
            NBioBSPJNI.FIR_HANDLE hSavedFIR = bsp.new FIR_HANDLE();
            bsp.Capture(hSavedFIR);
            //  bsp.Enroll(hSavedFIR, null);
            if (bsp.IsErrorOccured() == false) {
                NBioBSPJNI.FIR_TEXTENCODE textSavedFIR = bsp.new FIR_TEXTENCODE();
                bsp.GetTextFIRFromHandle(hSavedFIR, textSavedFIR);
                fingerprint = new Fingerprint();
                fingerprint.setFinger("0");
                fingerprint.setFir(textSavedFIR.TextFIR);
                threadDeviceController.start();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi entcontrado nenhum dispositivo!", "Atenção", JOptionPane.WARNING_MESSAGE);

        }

        System.err.println("scan");
        return fingerprint;

    }

    public static void main(String args[]) {
        RegisterFingerprint registerFingerprint = new RegisterFingerprint();
        registerFingerprint.scan();
    }
}
