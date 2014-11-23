
/*-
 * Classname:             ObserverInterface.java
 *
 * Version information:   1.0
 *
 * Date:                  26/02/2013 - 15:41:54
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

/**
 * Object para monitorar interfaces
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public interface ObserverInterface {

    /**
     * Atualiza observadores
     *
     * @param active interface ativa
     * @param object objeto de retorno
     */
    public void update(boolean active, Object object);
}//fim da classe ObserverInterface

