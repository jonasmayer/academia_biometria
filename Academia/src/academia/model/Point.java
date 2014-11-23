
/*-
 * Classname:             Point.java
 *
 * Version information:   (versão)
 *
 * Date:                  04/02/2013 - 17:27:04
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Descrição
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class Point  implements Comparable{

    private int codePoint;
    private int codeStudent;
    private Time entry;
    private Time exit;
    private Date day;

    public Point() {
    }

    public Point(int codePoint,
            int codeStudent,
            Time entry,
            Time exit,
            Date day) {
    }

    public Point(
            int codeStudent,
            Time entry,
            Time exit,
            Date day) {
    }

    public void setCodePoint(int codePoint) {
        this.codePoint = codePoint;
    }

    public void setCodeStudent(int codeStudent) {
        this.codeStudent = codeStudent;
    }

    public void setEntry(Time entry) {
        this.entry = entry;
    }

    public void setExit(Time exit) {
        this.exit = exit;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getCodePoint() {
        return codePoint;
    }

    public int getCodeStudent() {
        return codeStudent;
    }

    public Time getEntry() {
        return entry;
    }

    public Time getExit() {
        return exit;
    }

    public Date getDay() {
        return day;
    }
     public int compareTo(Object point) {
          Point otherPoint= (Point) point;
          int compareDay=this.getDay().compareTo(otherPoint.getDay());
        if(compareDay==0) {
            return this.getEntry().compareTo(otherPoint.getEntry());
       
        } else {
            return compareDay;}

    }//fim do método compareTo
}//fim da classe Point
