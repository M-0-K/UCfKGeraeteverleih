import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 06.03.2022
 * @author Moritz Kockert
 */

public class Diagramme extends JFrame {
  // Anfang Attribute
  // Ende Attribute
  
  public Diagramme() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1252; 
    int frameHeight = 671;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Diagramme");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    
    this.add(new MyDraw());

		this.setVisible(true);
  } // end of public Diagramme
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Diagramme();
    
    
  } // end of main
  
  public class MyDraw extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawLine(0, 0, 200, 200);
		}
	}
  
  // Ende Methoden
} // end of class Diagramme

