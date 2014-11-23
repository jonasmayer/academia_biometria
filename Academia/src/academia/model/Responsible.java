
/*-
 * Classname:             Responsible.java
 *
 * Version information:   (versão)
 *
 * Date:                  04/02/2013 - 18:25:00
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.model;

/**
 * Descrição
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class Responsible {

    private int codeResponsible;
    private int codeStudent;
    private String name;
    private String rg;
    private String phone;

    public Responsible() {
    }

    public Responsible(int codeResponsible, int codeStudent, String name, String rg, String phone) {
        this.codeResponsible = codeResponsible;
        this.codeStudent = codeStudent;
        this.name = name;
        this.rg = rg;
        this.phone = phone;
    }

    public Responsible(int codeStudent, String name, String rg, String phone) {

        this.codeStudent = codeStudent;
        this.name = name;
        this.rg = rg;
        this.phone = phone;
    }

    public void setCodeResponsible(int codeResponsible) {
        this.codeResponsible = codeResponsible;
    }

    public void setCodeStudent(int codeStudent) {
        this.codeStudent = codeStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCodeResponsible() {
        return codeResponsible;
    }

    public int getCodeStudent() {
        return codeStudent;
    }

    public String getName() {
        return name;
    }

    public String getRG() {
        return rg;
    }

    public String getPhone() {
        return phone;
    }
}//fim da classe Responsible

