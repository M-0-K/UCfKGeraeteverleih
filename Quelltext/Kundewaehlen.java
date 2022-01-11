import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.01.2022
 * @author 
 */

public class Kundewaehlen extends JDialog {
  // Anfang Attribute
  // Ende Attribute
  
  public Kundewaehlen(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Kundewaehlen");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    // Ende Komponenten
    
    setResizable(false);
    setVisible(true);
  } // end of public Kundewaehlen
  
  // Anfang Methoden
  // Ende Methoden
  
} // end of class Kundewaehlen
