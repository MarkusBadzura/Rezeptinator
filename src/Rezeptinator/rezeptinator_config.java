package Rezeptinator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Rezeptinator Konfigurationsdatei
 * @author Markus Badzura
 * @version 1.0.009
 */
public class rezeptinator_config 
{
    //////////////////////////////////////////////////////////////////
    //                                                              //
    // Deklaration und Initialisation config                        //
    //                                                              //
    //////////////////////////////////////////////////////////////////       
    private File configfile = new File("xml/config.xml");
    private rezeptinator_errorlog err = new rezeptinator_errorlog();
    private ArrayList configobjekt;
    private String name, wert;
    /**
     * Konstruktor Config-Datei-Leser
     * @author Markus Badzura
     * @since 1.0.007
     */
    public void rezeptinator_config()
    {
        if (configfile.exists())
            readConfig();
        else
            writeNewConfig();
    }
    /**
     * Schnittstelle Übergabe sämtlicher Config-Werte
     * @return ArrayList mit allen Config-Werten
     * @author Markus Badzura
     * @since 1.0.007
     */
    public ArrayList getConfigObjekt()
    {
        return configobjekt;
    }
    /**
     * Neuinitialisierung der Config-Datei
     * @author Markus Badzura
     * @since 1.0.007
     */
    public void rewriteConfig()
    {
        configfile.delete();
        writeNewConfig();
    }
    /**
     * Config-XML auslesen und ArrayList der Config-Werte erzeugen
     * @author Markus Badzura
     * @since 1.0.009
     */
    private void readConfig()
    {
        configobjekt = new ArrayList();
	SAXBuilder builder = new SAXBuilder();
        try 
        {
            Document document = (Document) builder.build(configfile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("content");         
            for (int i = 0; i < list.size(); i++) 
            {    
                Element node = (Element) list.get(i);
                name = node.getAttribute("name").getValue();
                wert = node.getChild("wert").getTextTrim();
                rezeptinator_config_conf temp = 
                        new rezeptinator_config_conf(name,wert);
                configobjekt.add(temp);
            }
        } 
        catch (JDOMException | IOException e) 
        {
            err.schreibe(e.toString(), "Config lesen");
        }        
    }
    /**
     * Configdatei erstmalig anlegen
     * @author Markus Badzura
     * @since 1.0.009
     */
    private void writeNewConfig()
    {
        Element root = new Element("config");
        Document doc = new Document(root);
        root.addContent(new Element("content")
                .setAttribute("name", "version")
                .addContent(new Element("wert").addContent("1.0")));
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        try
        {
            out.output(doc, new FileOutputStream(configfile));        
        }
        catch (FileNotFoundException e)
        {
            err.schreibe(e.toString(), "Config schreiben");
        }
        catch (IOException e)
        {
            err.schreibe(e.toString(), "Config schreiben");
        }
    }
}
