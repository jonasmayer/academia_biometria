
/*-
 * Classname:             SubjectInterface.java
 *
 * Version information:   (versão)
 *
 * Date:                  26/02/2013 - 15:42:20
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

/**
 * Subject
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public interface SubjectInterface {

    public void registerObserver(ObserverInterface o);

    public void removeObserver(ObserverInterface o);

    public void notifyObservers();
}
