
/*-
 * Classname:             Student.java
 *
 * Version information:   1.0
 *
 * Date:                  04/02/2013 - 18:17:33
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Descrição
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class Student implements Comparable {

    //código do aluno
    private int codeStudent;
    //nome 
    private String name;
    //sexo
    private String sex;
    //estado civil
    private String maritalStatus;
    //nacionalidade
    private String nationality;
    //cidade
    private String city;
    //UF estado
    private String uf;
    //endereço
    private String address;
    //bairro
    private String district;
    //número
    private int number;
    //CEP
    private String CEP;
    //telefone
    private String phone;
    //celular
    private String cellPhone;
    //e-mail
    private String mail;
    //data de aniversário
    private Date dateOfBirth;
    //RG
    private String rg;
    //objetivo
    private String objective;
    //observação
    private String observation;


    public Student() {
    }

    public Student(int codeStudent, String name, String sex, String maritalStatus,
            String nationality, String city, String uf, String address, String district,
            int number, String CEP, String phone, String cellPhone, String mail,
            Date dateOfBirth, String rg, String objective, String observation) {
        this.codeStudent = codeStudent;
        this.name = name;
        this.sex = sex;
        this.maritalStatus = maritalStatus;

        this.nationality = nationality;
        this.city = city;
        this.uf = uf;
        this.address = address;
        this.district = district;

        this.number = number;
        this.CEP = CEP;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;

        this.rg = rg;
        this.objective = objective;
        this.observation = observation;
    }

    public Student(String name, String sex, String maritalStatus, String nationality,
            String city, String uf, String address, String district, int number,
            String CEP, String phone, String cellPhone, String mail, Date dateOfBirth,
            String rg, String objective, String observation) {

        this.name = name;
        this.sex = sex;
        this.maritalStatus = maritalStatus;

        this.nationality = nationality;
        this.city = city;
        this.uf = uf;
        this.address = address;
        this.district = district;

        this.number = number;
        this.CEP = CEP;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;

        this.rg = rg;
        this.objective = objective;
        this.observation = observation;
    }

    public void setCodeStudent(int codeStudent) {
        this.codeStudent = codeStudent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUF(String uf) {
        this.uf = uf;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRG(String rg) {
        this.rg = rg;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getCodeStudent() {
        return codeStudent;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCity() {
        return city;
    }

    public String getUF() {
        return uf;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public int getNumber() {
        return number;
    }

    public String getCEP() {
        return CEP;
    }

    public String getPhone() {
        return phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getMail() {
        return mail;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        GregorianCalendar today = new GregorianCalendar();
        int yearToday = today.get(Calendar.YEAR);
        int monthToday = today.get(Calendar.MONTH);
        int dayToday = today.get(Calendar.DAY_OF_MONTH);

        GregorianCalendar birth = new GregorianCalendar();
        birth.setTimeInMillis(dateOfBirth.getTime());
        int yearBirth = birth.get(Calendar.YEAR);
        int monthBirth = birth.get(Calendar.MONTH);
        int dayBirth = birth.get(Calendar.DAY_OF_MONTH);

        int age = yearToday - yearBirth;
        if (monthToday < monthBirth) {
            age--;
        } else if (monthToday == monthBirth) {
            if (dayToday < dayBirth) {
                age--;
            }

        }


        return age;
    }

    public String getRG() {
        return rg;
    }

    public String getObjective() {
        return objective;
    }

    public String getObservation() {
        return observation;
    }

    public int compareTo(Object student) {
        Student otherStudent = (Student) student;

        return this.getName().compareTo(otherStudent.getName());

    }//fim do método compareTo
}//fim da classe Student

