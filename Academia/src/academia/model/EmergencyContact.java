
/*-
 * Classname:             EmergencyContact.java
 *
 * Version information:   (versão)
 *
 * Date:                  04/02/2013 - 17:42:02
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
public class EmergencyContact {

    private int codeContact;
    private int codeStudent;
    private String name;
    private String phone;
    private String city;
    private String uf;
    private String relationship;

    public EmergencyContact() {
    }

    public EmergencyContact(int codeContact,
            int codeStudent,
            String name,
            String phone,
            String city,
            String uf,
            String relationship) {
        this.codeContact = codeContact;
        this.codeStudent = codeStudent;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.uf = uf;
        this.relationship = relationship;
    }

    public EmergencyContact(
            int codeStudent,
            String name,
            String phone,
            String city,
            String uf,
            String relationship) {

        this.codeStudent = codeStudent;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.uf = uf;
        this.relationship = relationship;
    }

    public void setCodeContact(int codeContact) {
        this.codeContact = codeContact;
    }

    public void setCodeStudent(int codeStudent) {
        this.codeStudent = codeStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUF(String uf) {
        this.uf = uf;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getCodeContact() {
        return codeContact;
    }

    public int getCodeStudent() {
        return codeStudent;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getUF() {
        return uf;
    }

    public String getRelationship() {
        return relationship;
    }
}//fim da classe EmergencyContact
