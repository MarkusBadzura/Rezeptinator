package Rezeptinator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Rezeptinator Auslesen des Hilfetextes aus XML-Datei
 * @author Markus Badzura
 * @version 1.0.006
 */
public class rezeptinator_hilfe_xmllesen 
{
    ///////////////////////////////////////////////////////////////////////////
    //                                                                       //
    // Deklaration Variablen                                                 //
    //                                                                       //
    ///////////////////////////////////////////////////////////////////////////    
    private String schlagwort, hilfelink, antwort;
    private List rht;
    private rezeptinator_errorlog err = new rezeptinator_errorlog();
    /**
     * Hilfethemen aus XML auslesen und in Hilfethema wandeln.
     * Speichern in List
     * @author Markus Badzura
     * @since 1.0.006
     */
    public void hilfeXmlAuslesen()
    {
	SAXBuilder builder = new SAXBuilder();
	File xmlFile = new File("xml/help.xml");
        try 
        {
            rht = new ArrayList();
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("Hilfetext");         
            for (int i = 0; i < list.size(); i++) 
            {    
                Element node = (Element) list.get(i);
                schlagwort = node.getChild("Schlagwort").getText();
                hilfelink = node.getChild("Hilfelink").getText();
                antwort = node.getChild("Antwort").getText();	
                rezeptinator_hilfe_text temp = new rezeptinator_hilfe_text(schlagwort, hilfelink, antwort);
                rht.add(temp);
            }
        } 
        catch (JDOMException | IOException e) 
        {
            err.schreibe(e.toString(), "hilfeXmlAuslesen");
        }
    } 
    /**
     * Hilfethemen übergeben
     * @return rht List&lt;rezeptinator_hilfe_text&gt;
     * @author Markus Badzura
     * @since 1.0.005
     */
    public List<rezeptinator_hilfe_text> gehHilfethemen()
    {
        return rht;
    }
}
