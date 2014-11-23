/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package academia.controller;

import com.nitgen.SDK.BSP.NBioBSPJNI;
import javax.swing.JOptionPane;

/**
 *
 * @author Jonas
 */
public class Device {

    private NBioBSPJNI bsp;
    private NBioBSPJNI.DEVICE_ENUM_INFO deviceEnumInfo;
    private volatile static Device uniqueInstance;

    public Device() {
        bsp = new NBioBSPJNI();


    }

    public static Device getInstance() {
        if (uniqueInstance == null) {
            synchronized (Device.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Device();
                }
            }
        }
        return uniqueInstance;
    }

    public void start() {
        try {
            deviceEnumInfo = bsp.new DEVICE_ENUM_INFO();
            bsp.EnumerateDevice(deviceEnumInfo);
            bsp.OpenDevice(deviceEnumInfo.DeviceInfo[0].NameID, deviceEnumInfo.DeviceInfo[0].Instance);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Não foi possível iniciar o dispositivo!", "Atenção", JOptionPane.ERROR_MESSAGE);

        }

    }

    public NBioBSPJNI getBSP() {
        if (bsp != null || !bsp.IsErrorOccured()) {
            return bsp;
        } else {
            start();
            return null;
        }
    }

    public void stop() {
        bsp.CloseDevice(deviceEnumInfo.DeviceInfo[0].NameID, deviceEnumInfo.DeviceInfo[0].Instance);

    }
}
