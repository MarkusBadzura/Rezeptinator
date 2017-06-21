package Rezeptinator;

/**
 * Rezeptinator Config-Objekte
 * @author Markus Badzura
 * @version 1.0.009
 */
public class rezeptinator_config_conf 
{
    //////////////////////////////////////////////////////////////////
    //                                                              //
    // Variablendeklaration                                         //
    //                                                              //
    //////////////////////////////////////////////////////////////////     
    private String name;
    private String wert;
    /**
     * Standardkonstruktor
     * @author Markus Badzura
     * @since 1.0.007
     */
    rezeptinator_config_conf(){};
    /**
     * Konstruktor für Config-Objekt
     * @param name String Name des Config-Punktes
     * @param wert String Wert des Config-Punktes
     * @author Markus Badzura
     * @since 1.0.007
     */
    rezeptinator_config_conf(String name, String wert)
    {
        this.name = name;
        this.wert = wert;
    }
    /**
     * Schnittstelle zum ändern eines Wertes
     * @param wert String geänderter Wert des Config-Punktes
     * @author Markus Badzura
     * @since 1.0.007
     */
    public void setWert(String wert)
    {
        this.wert = wert;
    }
    /**
     * Schnittstelle zum Auslesen des Config-Punktes name
     * @return name als String
     * @author Markus Badzura
     * @since 1.0.007
     */
    public String getName()
    {
        return name;
    }
    /**
     * Schnittstelle zum Auslesen des Config-Punktes wert
     * @return wert als String
     * @author Markus Badzura
     * @since 1.0.007
     */
    public String getWert()
    {
        return wert;
    }
}
