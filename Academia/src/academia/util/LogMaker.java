
/*-
 * Classname:             LogMaker.java
 *
 * Version information:   1.0
 *
 * Date:                  25/01/2013 - 15:02:31
 *
 * author:                Jonas Mayer (jmayer13@hotmail.com)
 * Copyright notice:      (informações do método, pra que serve, idéia principal)
 */
package academia.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Classe responsável por gerar relatórios de erros
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class LogMaker {

    private static File directory;
    private static File file;
    private static PrintStream printStream;

    /**
     * Método que gera arquivo com o erro
     * @param exception recebe a excessão para gerar relatório 
     */
    public static void create(Exception exception) {
        //pega a data e hora atual
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        int day = gregorianCalendar.get(Calendar.DATE);
        int month = gregorianCalendar.get(Calendar.MONTH) + 1;
        int year = gregorianCalendar.get(Calendar.YEAR);
        int hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = gregorianCalendar.get(Calendar.MINUTE);
        int second = gregorianCalendar.get(Calendar.SECOND);
        //gera um número randomico para evitar conflito com arquivos já gerados 
        Random random = new Random();
        int randomNumber = random.nextInt(99);
        
        String title = day + "-" + month + "-" + year + "_" + hour + ";" + minute
                + ";" + second + "_" + randomNumber + ".err";

        //cria instancia do diretório
        directory = new File("error");
        //verifica se diret´´orio existe se não ele o cria
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                try {
                    throw new Exception("Error creating directory!");
                } catch (Exception ex) {
                    System.err.println("Erro ao gerar relatório de erro, não foi "
                            + "possivel criar o diretório");
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de"
                            + " erro, não foi possivel criar o diretório", "Erro!",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        }

        //cria arquivo com descrição do erro
        try {
            file = new File(directory, title);
            printStream = new PrintStream(file);
            exception.printStackTrace(printStream);

        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro ao criar Stream: file not found");
            fileNotFoundException.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de erro, "
                    + "não foi possivel criar o Stream!", "Erro!", JOptionPane.WARNING_MESSAGE);
        } catch (IOException iOException) {
            System.err.println("Erro ao criar Stream: falha de E/S");
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de erro, "
                    + "não foi possivel criar o Stream!", "Erro!", JOptionPane.WARNING_MESSAGE);
        } finally {
            printStream.close();
        }
    }//fim do método create
}//fim da classe LogMaker

