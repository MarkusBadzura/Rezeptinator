package Rezeptinator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Rezeptinator
 * @author Markus Badzura
 * @version 1.0.005
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
    private final String VERSIONSNUMMER = "1.0.005";
    private final String VERSION = "1.0";
    rezeptinator_hilfe rzh = new rezeptinator_hilfe();
    rezeptinator_hintergrund bgp,bgp_dia;
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
    ///////////////////////////////////////////////////////////////////////////
    //                                                                       //
    // Deklaration Dialogfenster HILFE - ABOUT                               //
    //                                                                       //
    ///////////////////////////////////////////////////////////////////////////   
    JDialog jd_about;
    JLabel lbl_about, lbl_about_programmierer, lbl_about_name, lbl_about_strasse,
            lbl_about_plz_ort, lbl_about_eMail, lbl_about_version, 
            lbl_about_version_nr, lbl_about_c;
    /**
     * Kontruktor Startfenster Rezeptinator
     * @author Markus Badzura
     * @since 1.0.002
     */
    public void rezeptinator_gui()
    {
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) 
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
        bgp = new rezeptinator_hintergrund();
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
        if (!rzh.isAktiv())
            rzh.rezeptinator_hilfe(VERSION);
    }
    /**
     * Allgemeine Progamminformationen öffnen
     * @author Markus Badzura
     * @since 1.0.003
     */
    private void oeffneAbout()
    {
        jd_about = new JDialog(this,"Allgemeine Informationen",true);
        jd_about.setSize(600,300);
        jd_about.setLayout(null);
        jd_about.setLocation(SCREENSIZE.width/2-300, SCREENSIZE.height/2-150);
        jd_about.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jd_about.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                jd_about.dispose();
            }
        });   
        bgp_dia = new rezeptinator_hintergrund();
        bgp_dia.setBounds(0,0,SCREENSIZE.width,SCREENSIZE.height);
        lbl_about = new JLabel("Allgemeine Informationen über Rezeptinator",JLabel.CENTER);
        lbl_about.setFont(lbl_about.getFont().deriveFont(18f));
        lbl_about.setFont(lbl_about.getFont().deriveFont(Font.BOLD));
        lbl_about.setForeground(Color.cyan);
        lbl_about.setBounds(0,0,500,30);
        lbl_about_programmierer = new JLabel("Programmiert von :", JLabel.RIGHT);
        lbl_about_programmierer.setFont(lbl_about_programmierer.getFont().deriveFont(14f));
        lbl_about_programmierer.setFont(lbl_about_programmierer.getFont().deriveFont(Font.BOLD));
        lbl_about_programmierer.setForeground(Color.cyan);
        lbl_about_programmierer.setBounds(0,50,180,25);
        lbl_about_name = new JLabel("Markus Badzura");
        lbl_about_name.setFont(lbl_about_name.getFont().deriveFont(14f));
        lbl_about_name.setFont(lbl_about_name.getFont().deriveFont(Font.BOLD));
        lbl_about_name.setForeground(Color.cyan);
        lbl_about_name.setBounds(185,50,250,25);
        lbl_about_strasse = new JLabel("Ungarnstraße 62");
        lbl_about_strasse.setFont(lbl_about_strasse.getFont().deriveFont(14f));
        lbl_about_strasse.setFont(lbl_about_strasse.getFont().deriveFont(Font.BOLD));
        lbl_about_strasse.setForeground(Color.cyan);
        lbl_about_strasse.setBounds(185,75,250,25);
        lbl_about_plz_ort = new JLabel("13349 Berlin");
        lbl_about_plz_ort.setFont(lbl_about_plz_ort.getFont().deriveFont(14f));
        lbl_about_plz_ort.setFont(lbl_about_plz_ort.getFont().deriveFont(Font.BOLD));
        lbl_about_plz_ort.setForeground(Color.cyan);
        lbl_about_plz_ort.setBounds(185,100,350,25);  
        lbl_about_eMail = new JLabel("markus.badzura@markus-badzura.info");
        lbl_about_eMail.setFont(lbl_about_eMail.getFont().deriveFont(14f));
        lbl_about_eMail.setFont(lbl_about_eMail.getFont().deriveFont(Font.BOLD));
        lbl_about_eMail.setForeground(Color.cyan);
        lbl_about_eMail.setBounds(185,140,350,25);         
        lbl_about_version = new JLabel("Version :",JLabel.RIGHT);
        lbl_about_version.setFont(lbl_about_version.getFont().deriveFont(14f));
        lbl_about_version.setFont(lbl_about_version.getFont().deriveFont(Font.BOLD));
        lbl_about_version.setForeground(Color.cyan);
        lbl_about_version.setBounds(0,180,180,25); 
        lbl_about_version_nr = new JLabel(VERSIONSNUMMER);
        lbl_about_version_nr.setFont(lbl_about_version_nr.getFont().deriveFont(14f));
        lbl_about_version_nr.setFont(lbl_about_version_nr.getFont().deriveFont(Font.BOLD));
        lbl_about_version_nr.setForeground(Color.cyan);
        lbl_about_version_nr.setBounds(185,180,250,25);  
        lbl_about_c = new JLabel("(c) 2017 by Markus Badzura",JLabel.CENTER);
        lbl_about_c.setFont(lbl_about_c.getFont().deriveFont(14f));
        lbl_about_c.setFont(lbl_about_c.getFont().deriveFont(Font.BOLD));
        lbl_about_c.setForeground(Color.cyan);
        lbl_about_c.setBounds(0,220,500,25);         
        jd_about.add(lbl_about_c);
        jd_about.add(lbl_about_version_nr);
        jd_about.add(lbl_about_version);
        jd_about.add(lbl_about_eMail);
        jd_about.add(lbl_about_plz_ort);
        jd_about.add(lbl_about_strasse);
        jd_about.add(lbl_about_name);
        jd_about.add(lbl_about_programmierer);
        jd_about.add(lbl_about);
        jd_about.add(bgp_dia);
        jd_about.setVisible(true);
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
