package Rezeptinator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Rezeptinator Embedded Database H2
 * @author Markus Badzura
 * @version 1.0.008
 */
public class rezeptinator_database 
{
  private rezeptinator_errorlog err = new rezeptinator_errorlog();
  private Connection connection;
  private Statement statement;
  private ResultSet resultSet; 
  private String version;
  /**
   * Standardkonstruktor zur Objekterstellung
   * @author Markus Badzura
   * @since 1.0.008
   */
  rezeptinator_database(){}  
  /**
   * Ermitteln der in der Datenbank hinterlegen Versionsnummer
   * @author Markus Badzura
   * @since 1.0.008
   */
  public void determineVersion()
  {
    openDatabase();
    checkVersion();
    closeDatabase();
  }
  /**
   * Datenbankverbindung öffnen
   * @author Markus Badzura
   * @since 1.0.008
   */
  private void openDatabase()
  {
    try
    {
      Class.forName("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:./db/"
              + "rezeptinator", "", "");
    }
    catch(SQLException | ClassNotFoundException e)
    {
      err.schreibe(e.toString(), "openDatabse");
    }
  }
  /**
   * Wenn Tabelle version nicht existiert, dann anlegen. 
   * Überprüfen, ob ein Eintrag in der Tabelle existiert. Wenn ja,
   * dann den Wert auslesen, wenn nein, dann die Version 1.0 eintragen.
   * @author Markus Badzura
   * @since 1.0.008
   */
  private void checkVersion()
  {
    int anzahl = 0;
    try
    {
      statement = connection.createStatement();
      String query = "CREATE TABLE IF NOT EXISTS version ("
              + "version CHAR(9))";
      statement.executeUpdate(query);
      resultSet = statement.executeQuery("SELECT COUNT(*) AS anzahl "
              + "FROM version");
      while(resultSet.next())
      {
        anzahl = resultSet.getInt("anzahl");
      }
      if (anzahl == 1)
      {
        resultSet = statement.executeQuery("SELECT * FROM version");
        while(resultSet.next())
        {
          version = resultSet.getString("version");
        }
      }
      else
      {
        query = "INSERT INTO version (version) VALUES ('1.0')";
        statement.executeQuery(query);
        version = "1.0";
      }
      System.out.println(anzahl);
      System.out.println(version);
    }
    catch(SQLException e)
    {
      err.schreibe(e.toString(), "checkVersion");
    }
  }
  /**
   * Datenbankverbindung schließen
   * @author Markus Badzura
   * @since 1.0.008
   */
  private void closeDatabase()
  {
    try
      {
          connection.close();
      }
      catch(SQLException e)
      {
        err.schreibe(e.toString(),"closeDatabase");         
      }    
  }
}
