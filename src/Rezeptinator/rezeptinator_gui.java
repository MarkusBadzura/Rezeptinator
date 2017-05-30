package Rezeptinator;

import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Rezeptinator
 * @author Markus Badzura
 * @version 1.0.003
 */
public class rezeptinator_gui extends JFrame implements ActionListener, KeyListener
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
    ///////////////////////////////////////////////////////////////////////////
    //                                                                       //
    // Deklaration MenuBar                                                   //
    //                                                                       //
    ///////////////////////////////////////////////////////////////////////////    
    Border bo_menuBar = new LineBorder(Color.BLACK);
    JMenuBar jmb;
    JMenu jm_datei, jm_hilfe;
    JMenuItem jmi_datei_beenden,
            jmi_hilfe_hilfe, jmi_hilfe_about;
    /**
     * Kontruktor Startfenster Rezeptinator
     * @author Markus Badzura
     * @since 1.0.002
     */
    @SuppressWarnings("UseSpecificCatch")
    public void rezeptinator_gui()
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
            System.err.println("Setting Look and Feel Failed");
        }
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
        this.add(bgp);
        this.setSize(SCREENSIZE);
        this.setIconImage(ICON.getImage());
        this.setMinimumSize(SCREENSIZE);
        setMenuBar();
        setVisible(true);
    }
    /**
     * Setzen der Menüleiste
     * @author Markus Badzura
     * @since 1.0.003
     */
    private void setMenuBar()
    {
        jmb = new JMenuBar();
        jmb.setBorder(bo_menuBar);
        jm_datei = new JMenu("Datei");
        jm_datei.setMnemonic('D');
        jmi_datei_beenden = new JMenuItem("Beenden",'B');
        jmi_datei_beenden.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        jmi_datei_beenden.addActionListener(this);
        jm_datei.add(jmi_datei_beenden);
        jm_hilfe = new JMenu("Hilfe");
        jm_hilfe.setMnemonic('H');
        jmi_hilfe_hilfe = new JMenuItem("Hilfe",'H');
        jmi_hilfe_hilfe.setAccelerator(KeyStroke.getKeyStroke("F1"));
        jmi_hilfe_hilfe.addActionListener(this);
        jmi_hilfe_about = new JMenuItem("About",'a');
        jmi_hilfe_about.addActionListener(this);
        jm_hilfe.add(jmi_hilfe_hilfe);
        jm_hilfe.add(jmi_hilfe_about);
        jmb.add(jm_datei);
        jmb.add(jm_hilfe);
        this.setJMenuBar(jmb);
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
    /**
     * Hilfefenster öffnen
     * @author Markus Badzura
     * @since 1.0.003
     */
    private void oeffneHilfe()
    {
        //@todo Hilfefenster öffnen
    }
    /**
     * Allgemeine Progamminformationen öffnen
     * @author Markus Badzura
     * @since 1.0.003
     */
    private void oeffneAbout()
    {
        //@todo Dialogfenster about öffnen
    }
    /**
     * Action-Listener für Menü- und Button-Ereignisses
     * @param e Auslöserobjekt
     * @author Markus Badzura
     * @since 1.0.003
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Menüpunkt DATEI - BEENDEN
        if (e.getSource() == jmi_datei_beenden)
        {
            exit();
        }
        // Menüpunkt HILFE - HILFE
        if (e.getSource() == jmi_hilfe_hilfe)
        {
            oeffneHilfe();
        }
        // Menüpunkt HILFE - About
        if (e.getSource() == jmi_hilfe_about)
        {
            oeffneAbout();
        }
    }
    /**
     * KeyListener Aktion für keyTyped
     * @param e Auslösende Tasten
     * @author Markus Badzura
     * @since 1.0.003
     */
    @Override
    public void keyTyped(KeyEvent e) 
    {
        // Keine Aktionen
    }
    /**
     * KeyListener Aktion für keyPressed
     * @param e Auslösende Tasten
     * @author Markus Badzura
     * @since 1.0.003
     */
    @Override
    public void keyPressed(KeyEvent e) 
    {
        // Funktionstaste F1
        if (e.getKeyCode() == KeyEvent.VK_F1)
        {
            oeffneHilfe();
        }
    }
    /**
     * KeyListener Aktion für keyReleased
     * @param e Auslösende Tasten
     * @author Markus Badzura
     * @since 1.0.003
     */
    @Override
    public void keyReleased(KeyEvent e) 
    {
        // Keine Aktionen
    }
}
