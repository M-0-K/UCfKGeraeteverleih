import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 18.02.2022
 * @author 
 */

public class Kundebaerbeiten extends JDialog {
  // Anfang Attribute
  private JLabel lKunde = new JLabel();
  private JLabel jLabel1 = new JLabel();
  // Ende Attribute
  
  public Kundebaerbeiten(JFrame owner, boolean modal) { 
    // Dialog-Initialisierung
    super(owner, modal);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300; 
    int frameHeight = 414;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Kundebaerbeiten");
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    lKunde.setBounds(8, 8, 142, 23);
    lKunde.setText("Kunde...");
    lKunde.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(lKunde);
    jLabel1.setBounds(8, 40, 110, 20);
    jLabel1.setText("text");
    cp.add(jLabel1);
    // Ende Komponenten
    
    setResizable(false);
    setVisible(true);
  } // end of public Kundebaerbeiten
  
  // Anfang Methoden
  // Ende Methoden
  
} // end of class Kundebaerbeiten
