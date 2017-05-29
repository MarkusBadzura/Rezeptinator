package Rezeptinator;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Hintergrund für Startfenster
 * @author Markus Badzura
 * @version 1.0.002
 */
public class rezeptinator_hintergrund extends JPanel
{
    private Image img;
    /**
     * Konsturktur zum setzen des Image-Objektes
     * @author Markus Badzura
     * @since 1.0.002
     */
    public rezeptinator_hintergrund() 
    {

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("holz.jpg"));
        img = imageIcon.getImage();
    }
    /**
     * Zeichnen der Bildkomponente
     * @param g Graphics-Objekt
     * @author Markus Badzura
     * @since 1.0.002
     */
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);

    }
    /**
     * Image-Objekt weiterreichen
     * @return img Image
     * @author Markus Badzura
     * @since 1.0.002
     */
    public Image getBackgroundImage() {
        return img;
    }
}    

