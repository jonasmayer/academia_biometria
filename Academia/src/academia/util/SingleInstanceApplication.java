
/*-
 * Classname:             SingleInstanceApplication.java
 *
 * Version information:   1.0
 *
 * Date:                  10/01/2013 - 13:30:48
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Classe que evita que o programa seja aberto mais de uma vez
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class SingleInstanceApplication {

    //arquivo de bloqueio
    private static File fileLock;
    //stream de controle
    private static PrintWriter printWriter;

    //método main para teste
    public static void main(String args[]) throws InterruptedException {
        //checa se o arquivo pode ser deletado (não está em uso)
     
        if (check()) {
            //bloqueia o arquivo
            lock();
            //permite que o programa abra
            //quando o programa é encerrado
            //libera o arquivo
            close();
        } else {
            System.out.println("Não é possivel abrir!");
        }
    }//fim do método main

    /**
     * Checa se é possível abrir o programa
     * @return <code>boolean</code> com a resposta
     */
    public static boolean check() {
        //cria instância e tenta deletar o arquivo
        fileLock = new File("lock");

        //checa se arquivo existe
        if (!fileLock.exists()) {
            return true;
        }

        //retorna true se foi possível e false se não
        return fileLock.delete();
    }//fim do método check

    /**
     * Libera arquivo de lock
     */
    public static void close() {
        //fecha stream
        printWriter.close();
    }//fim do método close

    /**
     * Bloqueia arquivo
     */
    public static void lock() {
        try {
            //cria stream
            printWriter = new PrintWriter(fileLock);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }//fim do método lock
}//fim da classe SingleInstanceApplication

