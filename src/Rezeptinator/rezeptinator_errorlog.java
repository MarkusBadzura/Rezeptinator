package Rezeptinator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Rezeptinator - Errorlog-Datei
 * @author Markus Badzura
 * @version 1.0.006
 */
public class rezeptinator_errorlog 
{
    /**************************************************************************
    * Variablendeklaration                                                    *
    ***************************************************************************/      
    private String file;
    
    /**
     * Erzeugen des Dateinamen für den Errorlog
     * Konvention: Aktuelles Datum
     * Alle Errorlogeinträge eines Monats werden in einer Datei gespeichert
     * @author Markus Badzura
     * @since 1.0.006
     */
    public rezeptinator_errorlog()
    {
        LocalDate d = LocalDate.now();
        file = "err//"+ d.getYear()+"_"+d.getMonth() +"_errlog.txt";
    }
    /**
     * Error-Log eintrag schreiben
     * @param inhalt Fehlerbeschreibung/Information
     * @param art Klasse und Methode
     * @author Markus Badzura
     * since 1.0.006
     */
    public void schreibe(String inhalt, String art)
    {
        try 
        {
            FileWriter writer = new FileWriter(file,true);
            LocalDateTime t = LocalDateTime.now();
            String zeit = t.getDayOfMonth()+"-"+t.getHour()+":"+t.getMinute();
            writer.write(zeit+"\t"+art+"\t"+inhalt+"\r\n");
            writer.close();
        } 
        catch (IOException e) 
        {
        }        
    }    
}
