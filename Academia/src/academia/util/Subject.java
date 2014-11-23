
/*-
 * Classname:             Subject.java
 *
 * Version information:   (versão)
 *
 * Date:                  25/02/2013 - 13:31:43
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

/**
 * Subject genérico
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public interface Subject {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers();
}
