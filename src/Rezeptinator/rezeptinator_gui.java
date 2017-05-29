package Rezeptinator;

import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * Rezeptinator
 * @author Markus Badzura
 * @version 1.0.002
 */
public class rezeptinator_gui extends JFrame
{
    ///////////////////////////////////////////////////////////////////////////
    //                                                                       //
    // Deklaration ImageIcon - Objekt und Bildschirmgröße                    //
    //                                                                       //
    ///////////////////////////////////////////////////////////////////////////
    // Pfadangabe zum Image
    private static final URL URLICON = rezeptinator_gui.class.getResource("Icon.gif");
    // Erstellen ImageIcon-Objekt
    private static final ImageIcon ICON = new ImageIcon(URLICON);
    private static final Dimension SCREENSIZE = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();    
    /**
     * Kontruktor Startfenster Rezeptinator
     * @author Markus Badzura
     * @since 1.0.002
     */
    public void rezeptinator_gui()
    {
        this.setTitle("Rezeptinator v1.0");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLayout(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                exit();
            }
        });          
        rezeptinator_hintergrund bgp = new rezeptinator_hintergrund();
        bgp.setBounds(0,0,SCREENSIZE.width,SCREENSIZE.height);
        add(bgp);
        this.setSize(SCREENSIZE);
        this.setIconImage(ICON.getImage());
        this.setMinimumSize(SCREENSIZE);
        setVisible(true);
    }
     /**
     * Abfragedialog beim Beenden des Programmes, inclusive des Schließens
     * über ALT + F4 und dem Schließbutton über die Titelleiste.
     * @author Markus Badzura
     * @since 1.0.001
     */
    private void exit()
    {
        int result = JOptionPane.showConfirmDialog(null, "Möchten Sie wirklich beenden?",
                "Programm beenden", JOptionPane.YES_NO_OPTION);
        switch (result)
        {
            case JOptionPane.YES_OPTION:
                System.exit(0);
        }
    }      
}
