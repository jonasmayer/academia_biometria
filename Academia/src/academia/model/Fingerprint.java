/*- 
 * Classname:             Fingerprint.java 
 * 
 * Version information:   1.0
 * 
 * Date:                  21/07/2013 - 15:59:49 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package academia.model;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class Fingerprint {

    private int codeFingerPrint;
    private int codeStudent;
    private String finger = "" + 0;
    private String fir;

    public Fingerprint() {
    }

    public void setCodeFingerPrint(int codeFingerPrint) {
        this.codeFingerPrint = codeFingerPrint;
    }

    public void setCodeStudent(int codeStudent) {
        this.codeStudent = codeStudent;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

    public void setFir(String fir) {
        this.fir = fir;
    }

    public int getCodeFingerPrint() {
        return codeFingerPrint;
    }

    public int getCodeStudent() {
        return codeStudent;
    }

    public String getFinger() {
        return finger;
    }

    public String getFir() {
        return fir;
    }
}//fim da classe Fingerprint  

